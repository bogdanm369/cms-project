package com.abm.apps.userprofile.repository;

import com.abm.apps.userprofile.domain.EvUserProfileImpl;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<EvUserProfileImpl, String> {
    EvUserProfileImpl findOneByUsernameIgnoreCase(String username);

    EvUserProfileImpl findOneByEmailIgnoreCase(String email);
}
