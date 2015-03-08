package org.appbricks.model.person;

/**
 * Describes a persons contact type
 */
public abstract class Contact
    implements Comparable<Contact> {
    
    private int order = Integer.MAX_VALUE;
    private boolean isPrimary;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isPrimary() {
        return this.isPrimary;
    }
    
    public void setPrimary(boolean primary) {
        this.isPrimary = primary;
    }

    @Override
    public int compareTo(Contact o) {
        return (o.order<this.order) ? -1 : (o.order>this.order) ? 1 : 0;
    }
}
