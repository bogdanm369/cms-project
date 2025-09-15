package com.abm.apps.userprofile.domain;

import com.abm.apps.evcommons.beans.domain.userProfile.EvUserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user-profiles-collection")
public class EvUserProfileImpl implements EvUserProfile {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotNull
    @NotEmpty
    @NotBlank
    private String username;
    @NotNull
    @NotEmpty
    @NotBlank
    @JsonIgnore
    private String password;
    @Indexed(unique = true)
    @NotNull
    @NotEmpty
    @NotBlank
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstname;
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastname;
    @NotNull
    private Date registrationDate;
    @LastModifiedDate
    @JsonIgnore
    private Date lastModifiedDate;
}
