package com.abm.apps.userprofile.web.controller;

import com.abm.apps.evcommons.beans.domain.http.operationEnums.OperationResult;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvRequestWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.EvResponseWrapperImpl;
import com.abm.apps.evcommons.beans.domain.http.payloads.userProfile.AuthenticateUserRequest;
import com.abm.apps.evcommons.beans.domain.http.payloads.userProfile.AuthenticateUserResponse;
import com.abm.apps.evcommons.beans.domain.http.payloads.userProfile.CreateOrUpdateUserRequest;
import com.abm.apps.evcommons.beans.domain.userProfile.EvUserProfile;
import com.abm.apps.tools.flowtracer.logging.MethodLogging;
import com.abm.apps.userprofile.domain.EvUserProfileImpl;
import com.abm.apps.userprofile.services.impl.UserProfileServiceImpl;
import com.abm.apps.userprofile.util.validator.UserProfileValidator;
import com.abm.apps.userprofile.web.assembler.UserProfileResourceHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserProfileController {

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @Autowired
    private UserProfileValidator userProfileValidator;

    @Autowired
    private UserProfileResourceHelper userProfileResourceHelper;


    @PostMapping("/authenticateEvUser")
    public ResponseEntity<?> authenticateEvUser(@RequestBody EvRequestWrapperImpl<AuthenticateUserRequest> authenticateUserRequest) {

        AuthenticateUserRequest aur = null;

        if (authenticateUserRequest == null || (aur = authenticateUserRequest.getRequest()) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EvResponseWrapperImpl<AuthenticateUserResponse>().builder()
                    .response(AuthenticateUserResponse.builder().operationResult(OperationResult.FAILED).operationResultText("AuthenticationUserRequest is null").build()));
        }

        String username = aur.getUsername();
        String password = aur.getPassword();

        log.info("Authenticating user : [{}], with password [{}]", username, password);

        AuthenticateUserResponse authenticateResponse = null;

        if ("admin".equals(username) && "admin".equals(password)) {
            authenticateResponse = AuthenticateUserResponse.builder().operationResult(OperationResult.SUCCESS)
                    .operationResultText("Authentication Success !!!").build();
        } else {
            authenticateResponse = AuthenticateUserResponse.builder().operationResult(OperationResult.FAILED)
                    .operationResultText("Authentication Failed; Username and Password are incorrect").build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(new EvResponseWrapperImpl<AuthenticateUserResponse>().builder()
                .response(authenticateResponse).build());
    }

    @PutMapping("/createUpdateProfile")
    @MethodLogging
    public ResponseEntity<?> createUserProfile(@RequestBody EvRequestWrapperImpl<CreateOrUpdateUserRequest<EvUserProfileImpl>> createUpdateRequest) {

        userProfileValidator.checkNotNull(createUpdateRequest.getRequest(), "evUserProfileDTO");

        EvUserProfile userProfile = userProfileService.saveNewUserProfile(createUpdateRequest.getRequest().getEvUserProfile());

        return userProfileResourceHelper.buildGetOkResponse(new EvUserProfileImpl());
    }

    @PostMapping("/createUpdateProfile")
    @MethodLogging
    public ResponseEntity<?> updateUserProfile(@RequestBody EvRequestWrapperImpl<CreateOrUpdateUserRequest<EvUserProfileImpl>> createUpdateRequest) {

        userProfileValidator.checkNotNull(createUpdateRequest.getRequest(), "evUserProfileDTO");

        EvUserProfile userProfile = userProfileService.saveUpdatedUserProfile(createUpdateRequest.getRequest().getEvUserProfile());

        return userProfileResourceHelper.buildGetOkResponse(new EvUserProfileImpl());
    }


    @GetMapping("/userProfile/email/{email}")
    @MethodLogging
    public ResponseEntity<?> getUserProfileByEmail(@PathVariable("email") String email) {

        EvUserProfileImpl userProfile = userProfileService.findByEmail(email);

        return userProfileResourceHelper.buildGetOkOrNotFoundResponse(userProfile);
    }

    @GetMapping("/userProfile/userName/{userName}")
    @MethodLogging
    public ResponseEntity<?> getUserProfileByUserName(@PathVariable("userName") String username) {

        EvUserProfileImpl userProfile = userProfileService.findByUsername(username);

        return userProfileResourceHelper.buildGetOkOrNotFoundResponse(userProfile);
    }

    @GetMapping("/userProfile/id/{id}")
    @MethodLogging
    public ResponseEntity<?> getUserProfileById(@PathVariable("id") String id) {

        EvUserProfileImpl userProfile = userProfileService.findById(id);

        return userProfileResourceHelper.buildGetOkOrNotFoundResponse(userProfile);
    }

    @DeleteMapping("/userProfile/id/{id}")
    @MethodLogging
    public ResponseEntity<?> deleteUserProfileById(@PathVariable("id") String id) {

        userProfileService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @GetMapping("/echo")
    public String echoText() {
        return "Hello !";
    }

    /**
     * Subscription Related Operations
     */

    public ResponseEntity<?> createNewUserSubscription() {


        return null;
    }

    public ResponseEntity<?> cancelSubscriptionExtension() {

        return null;
    }


}
