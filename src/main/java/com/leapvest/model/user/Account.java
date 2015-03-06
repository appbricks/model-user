package com.leapvest.model.user;

import com.leapvest.model.person.Email;
import com.leapvest.model.person.Person;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Describes a user account. A user is considered to have registered
 * if more than one owner is associated with the account.
 */
public class Account {

    @DBRef
    private TreeMap<Integer, Person> owners = new TreeMap<>();

    public Account() {
    }
    
    public Person getPrimaryOwner() {
        return (this.owners.size() > 0 ? this.owners.firstEntry().getValue() : null);
    }
    
    public List<Person> getOwners() {
        
        List<Person> orderedOwners = new ArrayList<>();
        this.owners.entrySet().stream().forEach(e -> orderedOwners.add(e.getValue()));
        return orderedOwners;
    }
    
    public void addPrimaryOwner(Person owner) {
        this.owners.entrySet().removeIf(e -> e.getValue().equals(owner));
        this.owners.put((this.owners.size() == 0 ? 0 : this.owners.firstKey() - 1), owner);
    }
    
    public void addOwner(Person owner) {
        this.owners.entrySet().removeIf(e -> e.getValue().equals(owner));
        this.owners.put((this.owners.size() == 0 ? 0 : this.owners.lastKey() + 1), owner);
    }

    public Email getPrimaryEmail() {
        Person primaryOwner = this.getPrimaryOwner();
        return (primaryOwner != null ? primaryOwner.getPrimaryEmail() : null);
    }
    
    @Override
    public String toString() {
        
        if (this.owners.size() > 0) {
            StringBuilder sb = new StringBuilder('(');

            Email primaryEmail = this.getPrimaryEmail();
            if (primaryEmail != null)
                sb.append(primaryEmail.getAddress()).append(' ');
            
            sb.append('[');
            
            sb.append(this.owners.values().stream()
                    .map(o -> "'" + o.getFormalName() + "'").collect(Collectors.joining(", ")));
            
            sb.append(']');
            
            return sb.toString();
        }
        
        return "(unregistered)";
    }
}
