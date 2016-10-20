package org.appbricks.model.user;

import org.springframework.social.security.SocialUser;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * This class captures information about a user such as the user's OpenID
 * detail along with various OAuth detail from providers that user has
 * authorized access to.
 */
public class LoggedInUser
    extends SocialUser {
    
    private User user;

    private MultiValueMap<SocialProvider, SocialConnection> socialConnections = new LinkedMultiValueMap<>();
    
    public LoggedInUser(User user) {
        super(user.getLoginName(), user.getPassword(), user.getRoles());
        
        this.user = user;
    }

    public void addSocialConnection(SocialConnection socialConnection) {
        this.socialConnections.add(SocialProvider.getProvider(socialConnection.getProviderId()), socialConnection);
    }
    
    @Override
    public String toString() {
        return user.toString();
    }
}
