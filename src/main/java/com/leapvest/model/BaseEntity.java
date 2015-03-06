package com.leapvest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * The base class for all model beans that can be persisted
 */
@Document
@SuppressWarnings("serial")
public abstract class BaseEntity
    implements Serializable {
    
    @Id
    protected String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        
        return this == o ||
            (o != null && this.getClass().equals(o.getClass()) && this.id.equals(((BaseEntity) o).id));
    }
}
