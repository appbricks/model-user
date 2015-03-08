package org.appbricks.repository.user;

import org.appbricks.model.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for persisting objects of type
 * {@link org.appbricks.model.user.Role}
 */
public interface RoleRepository
    extends MongoRepository<Role, String> {
    
    Role findByName(String name);
}
