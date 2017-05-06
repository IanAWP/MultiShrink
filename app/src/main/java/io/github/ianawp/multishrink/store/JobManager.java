package io.github.ianawp.multishrink.store;

import android.net.Uri;

import java.util.List;

/**
 * Created by IanAWP on 20/04/2017.
 */

public interface JobManager {
    Job getJobByID(String ID);
    String createJob(List<Uri> uris, int vres, OutputFormat outputFormat);
    List<?extends Job> getJobs();
    void RemoveJob(Job job);
}
