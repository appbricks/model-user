package org.appbricks.repository.user;

import org.appbricks.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for persisting objects of 
 * type {@link org.appbricks.model.user.User}.
 */
@Repository
public interface UserRepository
    extends MongoRepository<User, String> {

    public User findByLoginName(String loginName);
}
