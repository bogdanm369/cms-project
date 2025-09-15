package com.abm.apps.userprofile.services.impl;

import com.abm.apps.evcommons.utils.helpers.EvStringHelper;
import com.abm.apps.userprofile.domain.EvUserProfileImpl;
import com.abm.apps.userprofile.repository.UserProfileRepository;
import com.abm.apps.userprofile.util.validator.UserProfileValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserProfileServiceImpl {

    @Autowired
    private UserProfileValidator userProfileValidator;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private EvStringHelper stringHelper;

    public EvUserProfileImpl findByEmail(String email) {

        userProfileValidator.checkNotNull(email, "email");

        EvUserProfileImpl result = userProfileRepository.findOneByEmailIgnoreCase(email);

        return result;
    }

    public EvUserProfileImpl findByUsername(String username) {

        userProfileValidator.checkNotNull(username, "username");

        EvUserProfileImpl result = userProfileRepository.findOneByUsernameIgnoreCase(username);

        return result;
    }

    public EvUserProfileImpl findById(String id) {

        userProfileValidator.checkNotNull(id, "id");

        EvUserProfileImpl result = userProfileRepository.findById(id).orElse(null);

        return result;
    }

    public void deleteById(String id) {
        deleteById(id, true);
    }

    public void deleteById(String id, boolean exceptionOnNotFound) {

        userProfileValidator.checkNotNull(id, "id");

        if (null == findById(id) && exceptionOnNotFound) {
            userProfileValidator.evExceptionHelper().throwValidationException(String.format("UserProfile with id [%s] does not exist", id));
        }

        userProfileRepository.deleteById(id);
    }

    public EvUserProfileImpl saveNewUserProfile(EvUserProfileImpl evUserProfileImpl) {

        userProfileValidator.genericValidateForAll(evUserProfileImpl, "evUserProfile");
        userProfileValidator.checkMustBeNull(evUserProfileImpl.getId(), "evUserProfile.id");

        if (null != userProfileRepository.findOneByEmailIgnoreCase(evUserProfileImpl.getEmail())) {
            userProfileValidator.evExceptionHelper().throwValidationException(String.format("Username with email [%s] already exists", evUserProfileImpl.getEmail()));
        }

        if (null != userProfileRepository.findOneByUsernameIgnoreCase(evUserProfileImpl.getUsername())) {
            userProfileValidator.evExceptionHelper().throwValidationException(String.format("Username with username [%s] already exists", evUserProfileImpl.getUsername()));
        }

        evUserProfileImpl.setPassword(stringHelper.encodeInMD5(evUserProfileImpl.getPassword()));

        userProfileRepository.save(evUserProfileImpl);

        return evUserProfileImpl;
    }

    /**
     * Saves an Updated UserProfile
     * The User Profile will first be fetched by Id, so EvUserProfile parameter must provide the Id
     * Afterwards, it will ensure that if a username/email change has occurred, those values don't already belong to another user
     *
     * @param updatedEvUserProfileImpl
     * @return
     */
    public EvUserProfileImpl saveUpdatedUserProfile(EvUserProfileImpl updatedEvUserProfileImpl) {


        userProfileValidator.genericValidateForAll(updatedEvUserProfileImpl, "evUserProfile");
        userProfileValidator.checkNotNull(updatedEvUserProfileImpl.getId(), "evUserProfile.Id");

        validateForUpdatedProfile(updatedEvUserProfileImpl);

        userProfileRepository.save(updatedEvUserProfileImpl);

        return updatedEvUserProfileImpl;
    }

    private void validateForUpdatedProfile(EvUserProfileImpl newEvUserProfileImpl) {

        EvUserProfileImpl existingUserProfile = userProfileRepository.findById(newEvUserProfileImpl.getId()).orElse(null);

        if (null == existingUserProfile) {
            userProfileValidator.evExceptionHelper().throwValidationException(String.format("No UserProfile found for id [%s]", newEvUserProfileImpl.getId()));
        }

        if (!StringUtils.equals(existingUserProfile.getEmail(), newEvUserProfileImpl.getEmail())) {
            if (null != userProfileRepository.findOneByEmailIgnoreCase(newEvUserProfileImpl.getEmail())) {
                userProfileValidator.evExceptionHelper().throwValidationException(String.format("E-mail [%s] is already in use", newEvUserProfileImpl.getEmail()));
            }
        }

        if (!StringUtils.equals(existingUserProfile.getUsername(), newEvUserProfileImpl.getUsername())) {
            userProfileValidator.evExceptionHelper().throwValidationException(String.format("Username change is not allowed; Received username : [%s]; Existing username : [%s]", newEvUserProfileImpl.getUsername(), existingUserProfile.getUsername()));
        }
    }

    /**
     * User Subscriptions Area
     */


}


