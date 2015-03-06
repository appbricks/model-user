package com.leapvest.repository.user;

import com.leapvest.model.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for persisting objects of type
 * {@link com.leapvest.model.user.Role}
 */
public interface RoleRepository
    extends MongoRepository<Role, String> {
    
    Role findByName(String name);
}
