package org.appbricks.model.user;

import org.appbricks.model.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes an authorization role
 */
@Document(collection = "roles")
public class Role
    extends BaseEntity
    implements GrantedAuthority {

    @Indexed(unique = true)
    private String name;
    
    private String description;

    private List<ResourceGroup> resourceGroups = new ArrayList<>();
    
    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void deny(String pattern, boolean recursive) {
        this.resourceGroups.add(new ResourceGroup(pattern, recursive, Permission.DENY));
    }
    
    public void allow(String pattern, boolean recursive) {
        this.resourceGroups.add(new ResourceGroup(pattern, recursive, Permission.ALLOW));
    }
    
    @Override
    public String getAuthority() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
