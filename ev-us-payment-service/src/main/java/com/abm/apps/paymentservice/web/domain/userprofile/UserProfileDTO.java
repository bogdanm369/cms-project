package com.abm.apps.paymentservice.web.domain.userprofile;

import lombok.Data;

@Data
public class UserProfileDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
