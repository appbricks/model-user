package com.leapvest.repository.person;

import com.leapvest.model.person.Person;
import com.leapvest.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository class for persisting objects of
 * type {@link com.leapvest.model.person.Person}.
 */
public interface PersonRepository
    extends MongoRepository<Person, String> {

    public User findById(String id);
}
