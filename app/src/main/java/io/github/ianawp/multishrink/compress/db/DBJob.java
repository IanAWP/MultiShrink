package io.github.ianawp.multishrink.compress.db;

import java.util.HashMap;

import io.github.ianawp.multishrink.compress.Job;
import io.github.ianawp.multishrink.compress.JobSettings;
import io.github.ianawp.multishrink.compress.JobStatus;

/**
 * Created by IanAWP on 21/04/2017.
 */

public class DBJob implements Job {
    @Override
    public HashMap<String, String> getJobList() {
        return null;
    }

    @Override
    public void setJobList(HashMap<String, String> jobList) {

    }

    @Override
    public JobStatus getStatus() {
        return null;
    }

    @Override
    public void setStatus(JobStatus status) {

    }

    @Override
    public JobSettings getSettings() {
        return null;
    }

    @Override
    public void setSettings(JobSettings settings) {

    }

    @Override
    public String getId() {
        return null;
    }
}
