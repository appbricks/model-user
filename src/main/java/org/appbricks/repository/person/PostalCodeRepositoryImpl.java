package org.appbricks.repository.person;

import org.appbricks.model.person.Country;
import org.appbricks.model.person.PostalCode;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of custom methods to load a
 * {@link org.appbricks.model.person.PostalCode} object.
 */
public class PostalCodeRepositoryImpl
    implements PostalCodeRepositoryEx {

    @Inject
    private CountryRepository countryRepository;

    @Inject
    private PostalCodeRepository postalCodeRepository;
    
    @Override
    public List<PostalCode> postalCodesForCountry(Country country)
        throws InvalidCountryException {

        Country c = countryRepository.read(country);
        
        List<PostalCode> postalCodes = postalCodeRepository.findByCountry(c);
        postalCodes.stream().forEach(p -> p.setCountry(c));
        
        return postalCodes;
    }

    @Override
    public List<PostalCode> readList(PostalCode postalCode)
        throws InvalidCountryException {

        Country c = countryRepository.read(postalCode.getCountry());
        
        List<PostalCode> postalCodes = (postalCode.getLocality() == null
            ? postalCodeRepository.findByCountryAndCode(c, postalCode.getCode())
            : postalCodeRepository.findByCountryAndCodeAndLocality(c, postalCode.getCode(), postalCode.getLocality()));
        
        postalCodes.stream().forEach(p -> p.setCountry(c));
        return postalCodes;
    }

    @Override
    public PostalCode readOne(PostalCode postalCode)
        throws InvalidCountryException, InvalidPostalCodeException {

        List<PostalCode> postalCodes = postalCodeRepository.readList(postalCode);
        
        if (postalCodes.size() == 0 || postalCodes.size() > 1)
            throw new InvalidPostalCodeException( "A unique postal code was not found for '" +
                postalCode.getCode() + "' in '" + postalCode.getCountry().getAlpha2Code() + "'." );
        
        return postalCodes.get(0);
    }
}
