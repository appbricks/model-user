package com.leapvest.repository.user;

import com.leapvest.model.user.SocialConnection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Repository class for persisting objects of
 * type {@link com.leapvest.model.user.SocialConnection}.
 */
@Repository
public interface SocialConnectionRepository
    extends MongoRepository<SocialConnection, String> {

    List<SocialConnection> findByUserId(String userId);

    List<SocialConnection> findByUserIdAndProviderId(String userId, String providerId);

    List<SocialConnection> findByProviderIdAndProviderUserId(String providerId, String providerUserId);

    SocialConnection findByUserIdAndProviderIdAndProviderUserId(String userId, String providerId, String providerUserId);

    List<SocialConnection> findByProviderIdAndProviderUserIdIn(String providerId, Collection<String> providerUserIds);
}
