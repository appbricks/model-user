package org.appbricks.model.user;

import org.appbricks.model.person.Email;

/**
 * Describes an individual that that requires
 * access via an application sub-system
 */
public class IndividualUser
    extends User {

    private String registrationEmail;

    private Account account = new Account();

    public IndividualUser() {
    }

    public IndividualUser(String loginName) {
        super(loginName);
    }

    public IndividualUser(String loginName, String email) {
        super(loginName);
        this.registrationEmail = email;
    }

    public String getEmail() {

        if (this.account == null) {
            Email email = this.account.getPrimaryEmail();
            if (email != null)
                return email.getAddress();
        }

        return this.registrationEmail;
    }

    public boolean isRegistered() {
        return this.account.getOwners().size() > 0;
    }

    public Account getAccount() {
        return this.account;
    }
}
