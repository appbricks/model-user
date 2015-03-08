package org.appbricks.repository.person;

import org.appbricks.model.person.Country;
import org.appbricks.model.person.PostalCode;

import java.util.List;

/**
 * Custom methods to load a {@link org.appbricks.model.person.PostalCode}.
 */
public interface PostalCodeRepositoryEx {

    public List<PostalCode> postalCodesForCountry(Country country)
        throws InvalidCountryException;

    public List<PostalCode> readList(PostalCode postalCode)
        throws InvalidCountryException;

    public PostalCode readOne(PostalCode postalCode)
        throws InvalidCountryException, InvalidPostalCodeException;
}
