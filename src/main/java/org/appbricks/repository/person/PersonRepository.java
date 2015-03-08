package org.appbricks.repository.person;

import org.appbricks.model.person.Person;
import org.appbricks.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository class for persisting objects of
 * type {@link org.appbricks.model.person.Person}.
 */
public interface PersonRepository
    extends MongoRepository<Person, String> {

    public User findById(String id);
}
