package com.leapvest.repository.person;

import com.leapvest.model.person.Country;
import com.leapvest.model.person.PostalCode;

import java.util.List;

/**
 * Custom methods to load a {@link com.leapvest.model.person.PostalCode}.
 */
public interface PostalCodeRepositoryEx {

    public List<PostalCode> postalCodesForCountry(Country country)
        throws InvalidCountryException;

    public List<PostalCode> readList(PostalCode postalCode)
        throws InvalidCountryException;

    public PostalCode readOne(PostalCode postalCode)
        throws InvalidCountryException, InvalidPostalCodeException;
}
