package org.appbricks.model.user;

import org.springframework.data.annotation.Transient;

import java.util.Map;

/**
 * Describes a group of resources
 */
public class ResourceGroup {

    private String pattern;
    private boolean recursive;

    private Permission permission;

    @Transient
    private Map<Integer, Resource> resources;

    public ResourceGroup(String pattern, boolean recursive, Permission permission) {
        this.pattern = pattern;
        this.recursive = recursive;
        this.permission = permission;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Map<Integer, Resource> getResources() {
        return resources;
    }
}
