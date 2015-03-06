package com.leapvest.model.person;

import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Describes a contact phone
 */
public class Phone
    extends Contact {

    public enum Type {
        mobile,
        land,
        office,
        before_hours,
        after_hours
    }

    private Type type;
    private String number;

    @DBRef
    private Country country;
    
    public Phone() {
    }

    public Phone(Type type, String number, Country country) {
        this.type = type;
        this.number = number;
        this.country = country;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        String dialCode = this.country.getDialCode();
        if (dialCode != null)
            sb.append('(').append(dialCode).append(") ");
        
        sb.append(this.number);
        sb.append(" (").append(this.type).append(')');
        
        return sb.toString();
    }
}
