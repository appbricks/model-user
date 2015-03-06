package com.leapvest.model.person;

/**
 * Describes and email contact
 */
public class Email
    extends Contact {

    private String address;
    private boolean verified;
    
    public Email(String address) {
        this.address = address;
        this.verified = false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    
    @Override
    public String toString() {
        return this.address;
    }
}
