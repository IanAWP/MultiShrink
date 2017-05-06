package io.github.ianawp.multishrink.store;

/**
 * Created by IanAWP on 4/05/2017.
 */

public interface Image {
    String getSourceUri();

    void setSourceUri(String sourceUri);

    String getTargetUri();

    void setTargetUri(String targetUri);

    boolean getIsComplete();

    void setIsComplete(boolean isComplete);

    String getImageName();
    void setImageName(String imageName);
}
