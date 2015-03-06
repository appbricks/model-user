package com.leapvest.repository.person;

import com.leapvest.model.person.Country;

/**
 * Custom methods to load a {@link com.leapvest.model.person.Country}.
 */
public interface CountryRepositoryEx {

    public Country read(Country country)
        throws InvalidCountryException;
}
