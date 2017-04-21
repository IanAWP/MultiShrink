package io.github.ianawp.multishrink.compress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by IanAWP on 20/04/2017.
 * A job has a list of source uris a list of destination names, job settings, job ID, status
 */

public class POJOJob implements Job {
    @Override
    public HashMap<String, String> getJobList() {
        return jobList;
    }

    @Override
    public void setJobList(HashMap<String, String> jobList) {
        this.jobList = jobList;
    }

    private HashMap<String,String> jobList;

    @Override
    public JobStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(JobStatus status) {
        this.status = status;
    }

    private JobStatus status = JobStatus.NOT_STARTED;

    @Override
    public JobSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(JobSettings settings) {
        this.settings = settings;
    }

    private JobSettings settings;

    @Override
    public String getId() {
        return id;
    }

    private final String id;
    public POJOJob(JobSettings settings, ArrayList<String> images, String id) {
        this.settings = settings;
        for (String image:images) {
            jobList.put(image, destinationName(image));
        }
        this.id=id;
    }

    private String destinationName(String image) {
        return UUID.randomUUID().toString();
    }


}

