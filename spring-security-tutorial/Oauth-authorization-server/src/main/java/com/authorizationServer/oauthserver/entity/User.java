package com.authorizationServer.oauthserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String firstName;
    private String lastName;
    @Email
    @NotBlank(message = "Email cnanot be blank")
    private String email;
    @Column(length = 60)
    @NotBlank(message = "Password cannot be blank")
    private String password;
    private String role;
    private boolean enabled = false;
}
