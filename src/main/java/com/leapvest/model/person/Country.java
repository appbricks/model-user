package com.leapvest.model.person;

import com.leapvest.model.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Countries and Codes
 */
@Document(collection = "ref_countries")
public class Country
    extends BaseEntity {

    @Indexed
    private String name;

    @Indexed
    private String alpha2Code;
    
    private String alpha3Code;
    private String numericCode;
    private String isoCode;

    private String dialCode;

    private String postalCodeDescription; // Postal or Zip
    private String provinceDescription; // County or State
    private String localityDescription; // Town

    public Country() {
    }

    public Country(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return this.alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return this.alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getNumericCode() {
        return this.numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getDialCode() {
        return this.dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public void setPostalCodeDescription(String postalCodeDescription) {
        this.postalCodeDescription = postalCodeDescription;
    }

    public String getPostalCodeDescription() {
        return this.postalCodeDescription;
    }

    public String getProvinceDescription() {
        return this.provinceDescription;
    }

    public void setProvinceDescription(String provinceDescription) {
        this.provinceDescription = provinceDescription;
    }

    public String getLocalityDescription() {
        return this.localityDescription;
    }

    public void setLocalityDescription(String localityDescription) {
        this.localityDescription = localityDescription;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass().equals(Country.class) && ((Country) o).alpha2Code.equals(this.alpha2Code);
    }

    @Override
    public int hashCode() {
        return this.alpha2Code.hashCode();
    }
}
