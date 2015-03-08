package org.appbricks.repository.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.appbricks.model.user.Role;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Changelogs for Roles document collection
 */
@ChangeLog
public class RoleChangeLog {
    
    @ChangeSet(order = "001", id = "03201015", author = "ms")
    public void addBasicRoles(MongoTemplate mongoTemplate) {

        Role admin =  new Role("admin");
        admin.allow("**", true);
        mongoTemplate.save(admin);

        Role user =  new Role("user");
        user.allow("**/user/**", true);
        mongoTemplate.save(user);
    }
}
