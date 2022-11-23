package com.security.springsecurityclient.controller;

import com.security.springsecurityclient.entity.User;
import com.security.springsecurityclient.entity.VerificationToken;
import com.security.springsecurityclient.event.RegistrationEvent;
import com.security.springsecurityclient.model.PasswordModel;
import com.security.springsecurityclient.model.UserModel;
import com.security.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(
                new RegistrationEvent(
                        user,
                        applicationUrl(request)
                )
        );
        return "Success";
    }
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")){
            return "User verified Successfully";
        }
        if (result.equalsIgnoreCase("expired")){
            return "token sent has expired";
        }
        return "Bad User";
    }
    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request){

        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);

        User user = verificationToken.getUser();
        resendVerificationTokenEMail(user, applicationUrl(request), verificationToken);
        return "verification link sent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if(user != null){
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user,token);
            url = passwordResetTokenMail(user,applicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
                               @RequestBody PasswordModel passwordModel){

        String result = userService.valdidatePasswordResetToken(token);

        if(!result.equalsIgnoreCase("valid")){
            return "Invalid Token";
        }
        if (result.equalsIgnoreCase("expired")){
            return "token sent has expired";
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);

        if(user.isPresent()){
            userService.changePassword(user.get(),passwordModel.getNewPassword());
            return "Password reset successfully";
        }else{
            return "Invalid Token";
        }

    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())){
            return "Invalid old Password";
        }
        //save password
        userService.changePassword(user,passwordModel.getNewPassword());
        return  "Password Changed Successfully";
    }
    private String passwordResetTokenMail(User user, String applicationUrl, String token) {

        String url = applicationUrl + "/savePassword?token=" + token;

        log.info("Click the link to set your password {}"
                , url
        );
        return  url;
    }


    //help methods
    private void resendVerificationTokenEMail(User user, String applicationUrl, VerificationToken token) {


        String url = applicationUrl + "/verifyRegistration?token=" + token.getToken();

        log.info("Click the link to verify your account: {}"
                , url
        );
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
