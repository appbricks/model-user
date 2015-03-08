package org.appbricks.model.user;

import java.util.*;
import java.util.regex.Pattern;

/**
 * A resource which a user will
 * be authorized to access.
 */
public class Resource {

    private String name;

    private Resource parent;
    private Map<String, Resource> children = new HashMap<>();

    private boolean isRoot = false;

    private static Map<String, Resource> roots = new HashMap<>();

    private Resource(String name) {
        this.name = name;
    }

    public static final synchronized Resource create(String path) {

        if (path == null)
            throw new IllegalArgumentException("Resource path cannot be null.");

        Resource parent = null;
        Resource resource = null;

        boolean isRooted = (path.charAt(0) == '/');

        StringTokenizer st = new StringTokenizer(path, "/");
        while (st.hasMoreTokens()) {

            String name = st.nextToken();
            if (isRooted) {

                resource = Resource.roots.get(name);
                if (resource == null) {
                    resource = new Resource(name);
                    resource.isRoot = true;
                    Resource.roots.put(name, resource);
                }
                isRooted = false;

            } else {

                if (parent == null)
                    resource = new Resource(name);
                else {
                    resource = parent.children.get(name);
                    if (resource == null) {
                        resource = new Resource(name);
                    }
                    resource.parent = parent;
                    parent.children.put(name, resource);
                }
            }

            parent = resource;
        }
        return resource;
    }

    public static final Collection<Resource> search(String pattern) {

        if (pattern == null)
            throw new IllegalArgumentException("Search pattern cannot be null.");

        if (pattern.charAt(0) == '/')
            return Resource.findResources(Resource.roots, pattern.substring(1).split("/"), 0);
        else
            return Resource.findResources(Resource.roots, pattern.split("/"), 0);
    }

    private static Collection<Resource> findResources(Map<String, Resource> resources, String[] patterns, int depth) {

        Collection<Resource> results = new ArrayList<>();

        if (depth < patterns.length) {

            String pattern = patterns[depth];
            boolean isLastPattern = (depth == patterns.length - 1);

            if (pattern.equals("**")) {

                if (isLastPattern)
                    return resources.values();
                else {
                    Collection<Resource> recursiveResults = new ArrayList<>();

                    // Recursively traverse and locate matching children

                    resources.entrySet().stream()
                        .forEach( entry ->
                            recursiveResults.addAll(entry.getValue().findChildRecursive(patterns[depth + 1])) );

                    recursiveResults.stream()
                        .forEach( resource ->
                            results.addAll(Resource.findResources(resource.children, patterns, depth + 2)) );
                }

            } else if (pattern.equals("*")) {

                if (isLastPattern) {

                    // Only leaf nodes should match the wildcard

                    resources.entrySet().stream()
                        .filter(entry -> entry.getValue().isLeaf())
                        .forEach(entry -> results.add(entry.getValue()));

                } else {

                    // Assume all resources match wildcard at current depth

                    resources.entrySet().stream()
                        .forEach( entry ->
                            results.addAll(Resource.findResources(entry.getValue().children, patterns, depth + 1)) );
                }

            } else  if (pattern.matches("^[-_0-9a-zA-Z]+$")) {

                // Perform a key lookup for non-regex pattern

                Resource resource = resources.get(pattern);
                if (resource != null) {

                    if (isLastPattern && resource.isLeaf()) {
                        results.add(resource);
                    } else {
                        results.addAll(Resource.findResources(resource.children, patterns, depth + 1));
                    }
                }

            } else {

                Pattern name = Pattern.compile(pattern);

                resources.entrySet().stream()
                    .forEach(entry -> {

                        if (name.matcher(entry.getKey()).find()) {

                            Resource resource = entry.getValue();
                            if (isLastPattern && resource.isLeaf()) {
                                results.add(resource);
                            } else {
                                results.addAll(entry.getValue().findResources(resource.children, patterns, depth + 1));
                            }
                        }
                    });
            }
        }

        return results;
    }

    private Collection<Resource> findChildRecursive(String pattern) {

        List<Resource> results = new ArrayList<>();

        Pattern name = Pattern.compile(pattern);
        children.entrySet().stream()
            .forEach(child -> {
                if (name.matcher(child.getKey()).find())
                    results.add(child.getValue());
                else
                    results.addAll(child.getValue().findChildRecursive(pattern));
            } );

        return results;
    }

    public String getName() {
        return name;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    @Override
    public String toString() {
        return parent==null
            ? (isRoot ? '/' + name : name)
            : parent.toString() + '/' + name;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass().equals(Resource.class) && o.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
