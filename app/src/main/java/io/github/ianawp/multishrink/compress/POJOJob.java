//package io.github.ianawp.multishrink.compress;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by IanAWP on 20/04/2017.
// * A job has a list of source uris a list of destination names, job settings, job ID, status
// */
//
//public class POJOJob implements Job {
//    private List<String> jobList;
//    private JobStatus status = JobStatus.NOT_STARTED;
//    private  OutputFormat format;
//    private  int resolution;
//
//    @Override
//    public List<String> getJobList() {
//        return jobList;
//    }
//
//
//
//
//    @Override
//    public JobStatus getStatus() {
//        return status;
//    }
//
//    @Override
//    public void setStatus(JobStatus status) {
//        this.status = status;
//    }
//
//
//
//    @Override
//    public String getId() {
//        return id;
//    }
//
//    @Override
//    public OutputFormat getFormat() {
//        return null;
//    }
//
//    @Override
//    public void setFormat(OutputFormat format) {
//        this.format=format;
//    }
//
//    @Override
//    public int getResolution() {
//        return resolution;
//    }
//
//    @Override
//    public void setResolution(int res) {
//        resolution=res;
//    }
//
//
//
//    private final String id;
//    public POJOJob( ArrayList<String> images, String id, int resolution, OutputFormat format) {
//      this.resolution = resolution;
//        this.format = format;
//        jobList.addAll(images);
//        this.id=id;
//    }
//
//
//    private String destinationName(String image) {
//        return UUID.randomUUID().toString();
//    }
//
//
//}
//
