package com.abm.apps.userprofile.web.dto;

import com.abm.apps.userprofile.domain.EvUserProfileImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvUserProfilePayloadDTO implements Serializable {
    private List<EvUserProfileImpl> evUserProfileImpls;
}
