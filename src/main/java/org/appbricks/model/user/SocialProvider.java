package org.appbricks.model.user;

/**
 * Social Authorization detail for a particulate user.
 */
public abstract class SocialProvider {

    private String name;

    public SocialProvider(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static final SocialProvider getProvider(String name) {

        if (name.equals(GoogleProvider.NAME)) {
            return new GoogleProvider();
        } else if (name.equals(LinkedinProvider.NAME)) {
            return new LinkedinProvider();
        } else if (name.equals(FacebookProvider.NAME)) {
            return new FacebookProvider();
        } else if (name.equals(TwitterProvider.NAME)) {
            return new TwitterProvider();
        }

        throw new IllegalArgumentException("Social provider name '" + name + "' is not recognized");
    }
}
