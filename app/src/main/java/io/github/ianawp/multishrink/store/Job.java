package io.github.ianawp.multishrink.store;

import java.util.List;

/**
 * Created by IanAWP on 5/05/2017.
 */

public interface Job {
    void RunJob();
    List<? extends Image> getAllImages();
    boolean getIsComplete();
    JobDescription getJobDescription();
    void deleteJob();
}
