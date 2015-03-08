package org.appbricks.model.person;

import org.appbricks.model.BaseEntity;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Describes postal codes (or Zip codes for US)
 */
@Document(collection = "ref_postal_codes")
@CompoundIndexes({
    @CompoundIndex(name = "postalcode_idx", def = "{'country': 1, 'code': 1, 'locality': 1}")
})
public class PostalCode
    extends BaseEntity {

    private String code;
    private String province;
    private String locality;
    
    @DBRef
    private Country country;

    public PostalCode() {
    }

    public PostalCode(Country country, String code) {
        
        this.country = country;
        this.code = code;
    }

    public PostalCode(Country country, String code, String locality) {
        
        this.country = country;
        this.code = code;
        this.locality = locality;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLocality() {
        return this.locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Override
    public String toString() {
        return this.code;
    }

    @Override
    public boolean equals(Object o) {
        
        if (o.getClass().equals(PostalCode.class)) {
            
            PostalCode postalCode = (PostalCode) o;
            
            return postalCode.country.getAlpha2Code().equals(this.country.getAlpha2Code()) &&
                postalCode.code.equals(this.code) &&
                postalCode.locality.equals(this.locality);
        }
        return  false;
    }

    @Override
    public int hashCode() {
        return (
            this.country.getAlpha2Code().hashCode() ^ 
            this.code.hashCode() ^ 
            this.locality.hashCode() );
    }
}
