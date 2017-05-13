package io.github.ianawp.multishrink.store;

import android.net.Uri;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by IanAWP on 4/05/2017.
 */

public interface ImageProcessor {
    void processImage(Image image, JobDescription description, OutputStream stream);
}
