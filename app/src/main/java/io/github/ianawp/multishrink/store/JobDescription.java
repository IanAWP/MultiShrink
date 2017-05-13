package io.github.ianawp.multishrink.store;

import java.util.Date;
import java.util.List;

/**
 * Created by IanAWP on 21/04/2017.
 */

public interface JobDescription {

    OutputFormat getFormat();
    void setFormat(OutputFormat format);

    int getMaxDimension();

    void setMaxDimension(int res);


    Date    getTimeStamp();

}
