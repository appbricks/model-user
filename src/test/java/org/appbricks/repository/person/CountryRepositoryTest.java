package org.appbricks.repository.person;

import org.appbricks.TestContext;
import org.appbricks.model.person.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(classes = { TestContext.class })
public class CountryRepositoryTest
    extends AbstractTestNGSpringContextTests {

    @Autowired
    private CountryRepository repository;

    @Test
    public void shouldTestEquality()
        throws Exception {
        
        Country us = repository.read(new Country("US"));
        Country test;

        test = new Country();
        test.setName("United States of America");
        assertThat(us).isEqualTo(repository.read(test));

        test = new Country();
        test.setAlpha2Code("US");
        assertThat(us).isEqualTo(repository.read(test));

        test = new Country();
        test.setAlpha3Code("USA");
        assertThat(us).isEqualTo(repository.read(test));

        test = new Country();
        test.setNumericCode("840");
        assertThat(us).isEqualTo(repository.read(test));

        test = new Country();
        test.setIsoCode("ISO 3166-2:US");
        assertThat(us).isEqualTo(repository.read(test));

        test = repository.read(test);
        assertThat(us.getName()).isEqualTo(test.getName());
        assertThat(us.getAlpha2Code()).isEqualTo(test.getAlpha2Code());
        assertThat(us.getAlpha3Code()).isEqualTo(test.getAlpha3Code());
        assertThat(us.getNumericCode()).isEqualTo(test.getNumericCode());
        assertThat(us.getIsoCode()).isEqualTo(test.getIsoCode());

        Country uk = repository.read(new Country("GB"));
        assertThat(us).isNotEqualTo(uk);
    }

    @Test(expectedExceptions = InvalidCountryException.class)
    public void invalidCountry()
        throws Exception {

        Country c = new Country();
        c.setName("Timbukto");
        repository.read(c);
    }
}
