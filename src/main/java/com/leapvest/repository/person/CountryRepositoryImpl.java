package com.leapvest.repository.person;

import com.leapvest.model.person.Country;

import javax.inject.Inject;

/**
 * Implementation of custom methods to load a 
 * {@link com.leapvest.model.person.Country} object.
 */
class CountryRepositoryImpl
    implements CountryRepositoryEx {

    @Inject
    private CountryRepository countryRepository;

    @Override
    public Country read(Country country)
        throws InvalidCountryException {

        Country c = null;
        
        if (country.getAlpha2Code() != null)
            c = this.countryRepository.findByAlpha2Code(country.getAlpha2Code());
        else if (country.getAlpha3Code() != null)
            c = this.countryRepository.findByAlpha3Code(country.getAlpha3Code());
        else if (country.getNumericCode() != null)
            c = this.countryRepository.findByNumericCode(country.getNumericCode());
        else if (country.getIsoCode() != null)
            c = this.countryRepository.findByIsoCode(country.getIsoCode());
        else if (country.getName() != null)
            c = this.countryRepository.findByName(country.getName());

        if (c == null)
            throw new InvalidCountryException("Invalid country: " + country);
        
        return c;
    }
}
