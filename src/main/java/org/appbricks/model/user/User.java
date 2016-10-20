package org.appbricks.model.user;

import org.appbricks.model.Constants;
import org.appbricks.model.person.Email;
import org.appbricks.model.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * Describes User of the system.
 */
@Document(collection = "users")
public abstract class User
    extends BaseEntity {

    @Indexed(unique = true)
    private String loginName;
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public User() {
    }

    public User(String loginName) {
        this.loginName = loginName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getLoginName() {
        return this.loginName == null ? super.getId() : this.loginName;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
    
    public boolean verifyPassword(String password) {
        return PASSWORD_ENCODER.matches(password, this.password);
    }

    public String getPassword() {
        return this.password == null ? Constants.EMPTY_STRING : this.password;
    }

    public LoggedInUser getLoggedInUser() {
        return new LoggedInUser(this);
    }

    @Override
    public String toString() {
        return this.loginName;
    }
}
