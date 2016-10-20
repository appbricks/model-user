package org.appbricks.model.user;

/**
 * Google authorization.
 */
public class GoogleProvider
    extends SocialProvider {

    public static final String NAME = "google";

    public GoogleProvider() {
        super(NAME);
    }
}
