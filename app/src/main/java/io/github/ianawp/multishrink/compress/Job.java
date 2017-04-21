package io.github.ianawp.multishrink.compress;

import java.util.HashMap;

/**
 * Created by IanAWP on 21/04/2017.
 */

public interface Job {
    HashMap<String, String> getJobList();

    void setJobList(HashMap<String, String> jobList);

    JobStatus getStatus();

    void setStatus(JobStatus status);

    JobSettings getSettings();

    void setSettings(JobSettings settings);

    String getId();
}
