package org.appbricks.model.person;

import org.appbricks.model.BaseEntity;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Describes personal details of an individual
 */
@Document(collection = "customers")
@CompoundIndexes({
    @CompoundIndex(name = "person_name_idx", def = "{'familyName': 1, 'givenName': 1, 'additionalName': 1}")
})
public class Person
    extends BaseEntity {

    private String familyName;
    private String givenName;
    private String additionalName;

    private String honorificPrefix;
    private String honorificSuffix;

    private Set<Contact> contacts = new TreeSet<>();

    public Person() {
    }

    public Person(
        String familyName,
        String givenName,
        String additionalName,
        String honorificPrefix,
        String honorificSuffix ) {
        
        this.familyName = familyName;
        this.givenName = givenName;
        this.additionalName = additionalName;
        this.honorificPrefix = honorificPrefix;
        this.honorificSuffix = honorificSuffix;
    }

    public Person(
        String familyName,
        String givenName,
        String additionalName,
        String honorificPrefix ) {

        this.familyName = familyName;
        this.givenName = givenName;
        this.additionalName = additionalName;
        this.honorificPrefix = honorificPrefix;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName.trim();
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName.trim();
    }

    public String getAdditionalName() {
        return additionalName;
    }

    public void setAdditionalName(String additionalName) {
        this.additionalName = additionalName.trim();
    }

    public String getHonorificPrefix() {
        return honorificPrefix;
    }

    public void setHonorificPrefix(String honorificPrefix) {
        this.honorificPrefix = honorificPrefix;
    }

    public String getHonorificSuffix() {
        return honorificSuffix;
    }

    public void setHonorificSuffix(String honorificSuffix) {
        this.honorificSuffix = honorificSuffix;
    }

    public String getFormalName() {

        StringBuilder sb = new StringBuilder();

        if (this.honorificPrefix != null) {
            sb.append(this.honorificPrefix);
            sb.append(!this.honorificPrefix.endsWith(".") ? ". " : ' ');
        }

        sb.append(this.familyName);
        if (this.givenName != null) {
            sb.append(", ").append(this.givenName);

            if (this.additionalName != null) {
                sb.append(' ').append(this.additionalName);
                if (this.additionalName.length() == 1)
                    sb.append(".");
            }
        }

        if (this.honorificSuffix != null) {
            sb.append(", ").append(this.honorificSuffix);
        }

        return sb.toString();
    }

    public String getName() {
        return this.givenName + ' ' + this.familyName;
    }

    public Email getPrimaryEmail() {
        return (Email) this.getContacts(Email.class, true);
    }
    
    public PostalAddress getPrimaryPostalAddress() {
        return (PostalAddress) this.getContacts(PostalAddress.class, true);
    }
    
    public Phone getPrimaryPhone() {
        return (Phone) this.getContacts(Phone.class, true);
    }

    public Collection<Contact> getContacts(Class type) {
        return contacts.stream()
            .filter(c -> c.getClass().equals(type)).collect(Collectors.toList());
    }

    public Contact getContacts(Class type, boolean isPrimary) {

        Optional<Contact> contact = contacts.stream()
            .filter(c -> c.getClass().equals(type) && c.isPrimary()).findFirst();

        return contact.isPresent() ? contact.get() : null;
    }

    public void addContact(Contact contact) {
        this.addContact(contact, false);
    }

    public void addContact(Contact contact, boolean isPrimary) {
        
        if (contact.getOrder() == Integer.MAX_VALUE)
            contact.setOrder(this.contacts.size());

        if (isPrimary) {
            Contact primaryContact = this.getContacts(contact.getClass(), true);
            if (primaryContact != null)
                primaryContact.setPrimary(false);

            contact.setPrimary(isPrimary);
        } else
            contact.setPrimary(this.getContacts(contact.getClass()).size() == 0);

        this.contacts.add(contact);
    }

    @Override
    public String toString() {
        return super.toString() + " (" + this.getFormalName() + ")";
    }
}
