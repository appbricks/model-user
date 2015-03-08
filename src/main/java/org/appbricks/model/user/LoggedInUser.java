package org.appbricks.model.user;

import org.springframework.social.security.SocialUser;

import java.util.*;

/**
 * This class captures information about a user such as the user's OpenID
 * detail along with various OAuth detail from providers that user has
 * authorized access to.
 */
public class LoggedInUser
    extends SocialUser {
    
    private User user;
    
    private Map<SocialAuthz, List<SocialConnection>> socialAuthorizations = new HashMap<>();
    
    public LoggedInUser(User user) {
        super(user.getLoginName(), user.getPassword(), user.getRoles());
        
        this.user = user;
    }
    
    @Override
    public String toString() {
        return user.toString();
    }
}
