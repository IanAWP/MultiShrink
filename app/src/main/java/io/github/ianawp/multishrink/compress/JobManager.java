package io.github.ianawp.multishrink.compress;

import android.net.Uri;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IanAWP on 20/04/2017.
 */

public interface JobManager {
    Job getJobByID(String ID);
    //List<Job> getJobByStatus(JobStatus status);
    String createJob(List<Uri> uris, int vres, OutputFormat outputFormat);
    List<Job> getAllJobs();
}
