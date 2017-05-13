package io.github.ianawp.multishrink.store;

/**
 * Created by IanAWP on 13/05/2017.
 */

public interface JobCallback {
    void OnUpdate(int current, int total);
}
