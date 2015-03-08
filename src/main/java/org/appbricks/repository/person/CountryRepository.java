package org.appbricks.repository.person;

import org.appbricks.model.person.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository class for persisting objects of 
 * type {@link org.appbricks.model.person.Country}.
 */
public interface CountryRepository
    extends MongoRepository<Country, String>, CountryRepositoryEx {

    public Country findByName(String name);
    public Country findByAlpha2Code(String alpha2Code);
    public Country findByAlpha3Code(String alpha3Code);
    public Country findByNumericCode(String numericCode);
    public Country findByIsoCode(String isoCode);
}
