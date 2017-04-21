package io.github.ianawp.multishrink.compress;

import java.util.HashMap;
import java.util.List;

/**
 * Created by IanAWP on 21/04/2017.
 */

public interface Job {
    List<String> getJobList();



    JobStatus getStatus();

    void setStatus(JobStatus status);

    String getKey();

    OutputFormat getFormat();
    void setFormat(OutputFormat format);

    int getResolution();

    void setResolution(int res);


}
