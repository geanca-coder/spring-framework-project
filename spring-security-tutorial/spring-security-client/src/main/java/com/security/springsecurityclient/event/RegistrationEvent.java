package com.security.springsecurityclient.event;

import com.security.springsecurityclient.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;
@Data
@Builder
public class RegistrationEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    public  RegistrationEvent(User user, String applicationUrl){
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
