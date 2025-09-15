package com.abm.apps.userprofile.util.validator;

import com.abm.apps.evcommons.utils.EvExceptionHelper;
import com.abm.apps.evcommons.utils.validators.EvObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileValidator extends EvObjectValidator {

    private final EvExceptionHelper evExceptionHelper;

    public UserProfileValidator(@Autowired EvExceptionHelper evExceptionHelper) {
        this.evExceptionHelper = evExceptionHelper;
    }

    public EvExceptionHelper evExceptionHelper() {
        return this.evExceptionHelper;
    }

}
