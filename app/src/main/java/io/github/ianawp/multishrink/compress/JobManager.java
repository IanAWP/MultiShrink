package io.github.ianawp.multishrink.compress;

import java.util.HashMap;

/**
 * Created by IanAWP on 20/04/2017.
 */

public interface JobManager {
    Job getJobByID(String ID);
    HashMap<String, Job> getJobByStatus(JobStatus status);
    String createJob(Job job);
}
