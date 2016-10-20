package org.appbricks.model.person;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Describes a postal address
 */
public class PostalAddress
    extends Contact {

    ArrayList<String> lines = new ArrayList<>();

    @DBRef
    private PostalCode postalCode;
    @DBRef
    private Country country;

    public PostalAddress() {
    }
    
    public PostalAddress(String[] lines, PostalCode postalCode, Country country) {
        this.lines.addAll(Arrays.asList(lines));
        this.postalCode = postalCode;
        this.country = country;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        this.lines.forEach(line -> sb.append(line).append(", "));
        sb.setLength(sb.length() - 2);
        
        if (this.postalCode.getLocality() != null)
            sb.append(", ").append(this.postalCode.getLocality());
        if (this.postalCode.getProvince() != null)
            sb.append(", ").append(this.postalCode.getProvince());
        if (this.postalCode.getCode() != null)
            sb.append(" ").append(this.postalCode.getCode());

        sb.append(", ").append(this.country);
        
        return sb.toString();
    }
}
