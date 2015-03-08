package org.appbricks.repository.person;

import org.appbricks.model.person.Country;
import org.appbricks.model.person.PostalCode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository class for persisting objects of
 * type {@link org.appbricks.model.person.PostalCode}.
 */
public interface PostalCodeRepository
    extends MongoRepository<PostalCode, String>, PostalCodeRepositoryEx {

    public List<PostalCode> findByCountry(Country country);
    public List<PostalCode> findByCountryAndCode(Country country, String code);
    public List<PostalCode> findByCountryAndCodeAndLocality(Country country, String code, String locality);
}
