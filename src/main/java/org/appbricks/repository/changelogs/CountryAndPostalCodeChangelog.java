package org.appbricks.repository.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.appbricks.model.person.Country;
import org.appbricks.model.person.PostalCode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Changelogs for Country and PostalCodes document collection
 */
@ChangeLog
public class CountryAndPostalCodeChangelog {

    @ChangeSet(order = "001", id = "02212015", author = "ms")
    public void uploadCountryAndPostalCode(MongoTemplate mongoTemplate)
        throws IOException {

        Resource data = new ClassPathResource("data/dialcodes-02212015.dat");
        BufferedReader reader = new BufferedReader(new InputStreamReader(data.getInputStream()));
        String line;
        
        Map<String, String> dialCodes = new HashMap<>();
        while ((line = reader.readLine()) != null) {

            String[] fields = line.split("\\|");
            dialCodes.put(fields[0], fields[1]);
        }
        
        data = new ClassPathResource("data/countries-02212015.dat");
        reader = new BufferedReader(new InputStreamReader(data.getInputStream()));

        Map<String, Country> countries = new HashMap<>();
        while ((line = reader.readLine()) != null) {

            String[] fields = line.split("\\|");

            Country country = new Country();
            country.setName(fields[0]);
            country.setAlpha2Code(fields[1]);
            country.setAlpha3Code(fields[2]);
            country.setNumericCode(String.format("%03d", Integer.parseInt(fields[3])));
            country.setIsoCode(fields[4]);
            country.setDialCode(dialCodes.get(fields[1]));

            if (fields.length > 5)
                country.setPostalCodeDescription(fields[5]);
            if (fields.length > 6)
                country.setProvinceDescription(fields[6]);
            if (fields.length > 7)
                country.setLocalityDescription(fields[7]);

            mongoTemplate.save(country);
            countries.put(country.getAlpha2Code(), country);
        }

        data = new ClassPathResource("data/uszipcodes-02212015.dat");
        reader = new BufferedReader(new InputStreamReader(data.getInputStream()));

        while ((line = reader.readLine()) != null) {

            String[] fields = line.split("\\|");

            PostalCode postalCode = new PostalCode();
            postalCode.setCode(String.format("%05d", Integer.parseInt(fields[0])));
            postalCode.setProvince(fields[1]);
            postalCode.setLocality(fields[2]);
            postalCode.setCountry(countries.get(fields[3]));

            mongoTemplate.save(postalCode);
        }
    }
}
