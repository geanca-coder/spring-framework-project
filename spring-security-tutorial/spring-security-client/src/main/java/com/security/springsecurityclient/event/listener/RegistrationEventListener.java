package com.security.springsecurityclient.event.listener;

import com.security.springsecurityclient.entity.User;
import com.security.springsecurityclient.event.RegistrationEvent;
import com.security.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
public class RegistrationEventListener implements
        ApplicationListener<RegistrationEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        //Create the verification token for the user! with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        //send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        //sendVerificacionEmail()
        log.info("Click the link to verify your account: {}"
                , url
        );
    }
}
