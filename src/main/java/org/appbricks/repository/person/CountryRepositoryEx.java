package org.appbricks.repository.person;

import org.appbricks.model.person.Country;

/**
 * Custom methods to load a {@link org.appbricks.model.person.Country}.
 */
public interface CountryRepositoryEx {

    public Country read(Country country)
        throws InvalidCountryException;
}
