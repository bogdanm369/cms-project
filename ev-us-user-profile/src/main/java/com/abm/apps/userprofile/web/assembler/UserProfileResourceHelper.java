package com.abm.apps.userprofile.web.assembler;

import com.abm.apps.evcommons.utils.EvExceptionHelper;
import com.abm.apps.userprofile.domain.EvUserProfileImpl;
import com.abm.apps.userprofile.web.dto.EvUserProfilePayloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileResourceHelper {

    @Autowired
    private EvExceptionHelper exceptionHelper;

    public ResponseEntity<EvUserProfilePayloadDTO> buildGetOkResponse(EvUserProfileImpl evUserProfileImpl) {

        if (null == evUserProfileImpl) {
            exceptionHelper.throwValidationExceptionForNullField("evUserProfile");
        }

        return buildGetOkResponse(new ArrayList<EvUserProfileImpl>() {{
            add(evUserProfileImpl);
        }});
    }

    public ResponseEntity<EvUserProfilePayloadDTO> buildGetOkResponse(List<EvUserProfileImpl> evUserProfileImpls) {

        if (CollectionUtils.isEmpty(evUserProfileImpls)) {
            exceptionHelper.throwValidationExceptionForNullField("evUserProfiles(list)");
        }

        return ResponseEntity.ok(new EvUserProfilePayloadDTO((evUserProfileImpls)));
    }

    public ResponseEntity<EvUserProfilePayloadDTO> buildGetOkOrNotFoundResponse(EvUserProfileImpl evUserProfileImpl) {

        if (null == evUserProfileImpl) {
            return ResponseEntity.notFound().build();
        }
        return buildGetOkResponse(evUserProfileImpl);
    }

}
