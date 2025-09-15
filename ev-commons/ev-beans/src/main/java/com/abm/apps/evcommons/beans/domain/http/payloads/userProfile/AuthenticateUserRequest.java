package com.abm.apps.evcommons.beans.domain.http.payloads.userProfile;

import com.abm.apps.evcommons.beans.domain.http.payloads.generic.EvGenericRequest;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticateUserRequest implements EvGenericRequest {
    private String username;
    private String password;
}
