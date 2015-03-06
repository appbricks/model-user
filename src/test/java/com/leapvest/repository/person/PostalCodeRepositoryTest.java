package com.leapvest.repository.person;

import com.leapvest.TestContext;
import com.leapvest.model.person.Country;
import com.leapvest.model.person.PostalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(classes = { TestContext.class })
public class PostalCodeRepositoryTest
    extends AbstractTestNGSpringContextTests {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PostalCodeRepository postalCodeRepository;

    @Test
    public void shouldTestEquality()
        throws Exception {

        Country us = countryRepository.read(new Country("US"));

        PostalCode us01450 = new PostalCode(new Country("US"), "01450", "Groton");
        PostalCode test = postalCodeRepository.readOne(us01450);

        assertThat(us01450).isEqualTo(test);

        assertThat(test.getCode()).isEqualTo("01450");
        assertThat(test.getProvince()).isEqualTo("MA");
        assertThat(test.getLocality()).isEqualTo("Groton");

        assertThat(us.getName()).isEqualTo(test.getCountry().getName());
        assertThat(us.getAlpha2Code()).isEqualTo(test.getCountry().getAlpha2Code());
        assertThat(us.getAlpha3Code()).isEqualTo(test.getCountry().getAlpha3Code());
        assertThat(us.getNumericCode()).isEqualTo(test.getCountry().getNumericCode());
        assertThat(us.getIsoCode()).isEqualTo(test.getCountry().getIsoCode());
        
        List<PostalCode> umCodes = postalCodeRepository.readList(new PostalCode(new Country("UM"), "96898"));
        assertThat(umCodes.size()).isEqualTo(11);

        List<String> localities = umCodes.stream().map(p -> p.getLocality()).collect(Collectors.toList());
        
        assertThat(localities).containsOnly(
            "Baker Island",
            "Howland Island",
            "Jarvis Island",
            "Johnston Atoll",
            "Kingman Reef",
            "Midway Islands",
            "Navassa Island",
            "Palmyra Atoll",
            "Wake Island",
            "Bajo Nuevo Bank",
            "Serranilla Bank");

        List<PostalCode> usCodes = postalCodeRepository.postalCodesForCountry(new Country("US"));
        assertThat(usCodes.size()).isEqualTo(42521);
        
        test = usCodes.get(0);
        assertThat(us.getName()).isEqualTo(test.getCountry().getName());
        assertThat(us.getAlpha2Code()).isEqualTo(test.getCountry().getAlpha2Code());
        assertThat(us.getAlpha3Code()).isEqualTo(test.getCountry().getAlpha3Code());
        assertThat(us.getNumericCode()).isEqualTo(test.getCountry().getNumericCode());
        assertThat(us.getIsoCode()).isEqualTo(test.getCountry().getIsoCode());
    }

    @Test(expectedExceptions = InvalidCountryException.class)
    public void invalidCountry()
        throws Exception {

        PostalCode p = new PostalCode();
        p.setCountry(new Country("ZZ"));
        p.setCode("01450");
        p.setLocality("Groton");
        postalCodeRepository.readOne(p);
    }

    @Test(expectedExceptions = InvalidPostalCodeException.class)
    public void invalidPostalCode()
        throws Exception {

        PostalCode p = new PostalCode();
        p.setCountry(new Country("US"));
        p.setCode("99999");
        p.setCode("mars");
        postalCodeRepository.readOne(p);
    }
}
