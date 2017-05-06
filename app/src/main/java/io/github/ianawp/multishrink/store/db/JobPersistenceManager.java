package io.github.ianawp.multishrink.store.db;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by IanAWP on 5/05/2017.
 */

public interface JobPersistenceManager {
    OutputStream getOutputStream(String scope, String key) throws IOException;
    Uri getUri(String scope, String key);
    void deleteScope(String scope);
    void delete(String scope, String key);

}
