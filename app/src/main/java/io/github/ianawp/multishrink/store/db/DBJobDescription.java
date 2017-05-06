package io.github.ianawp.multishrink.store.db;

import android.net.Uri;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.ianawp.multishrink.store.Image;
import io.github.ianawp.multishrink.store.JobDescription;
import io.github.ianawp.multishrink.store.ImageProcessor;
import io.github.ianawp.multishrink.store.JobStatus;
import io.github.ianawp.multishrink.store.OutputFormat;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by IanAWP on 21/04/2017.
 */

@Entity(
        nameInDb = "JOB_DESCRIPTION"
)
public class DBJobDescription implements JobDescription {
    @Id(autoincrement = true)
    private Long id;


    @NotNull
    @Convert(converter = OutputFormatConverter.class, columnType = String.class)
    private  OutputFormat format;

    @NotNull
    private  int resolution;

    @NotNull
    private Date timeStamp;



    @Generated(hash = 1183432296)
    public DBJobDescription(Long id, @NotNull OutputFormat format, int resolution, @NotNull Date timeStamp) {
        this.id = id;
        this.format = format;
        this.resolution = resolution;
        this.timeStamp = timeStamp;
    }

    @Generated(hash = 552110694)
    public DBJobDescription() {
    }

    @Override
    public OutputFormat getFormat() {
        return format;
    }

    @Override
    public void setFormat(OutputFormat format) {
        this.format=format;
    }

    @Override
    public int getResolution() {
        return resolution;
    }

    @Override
    public void setResolution(int res) {
        resolution=res;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Long getId() {
        return this.id;
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }



    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    static class OutputFormatConverter implements PropertyConverter<OutputFormat, String> {
        @Override
        public OutputFormat convertToEntityProperty(String databaseValue) {
            return OutputFormat.valueOf(databaseValue);
        }
        @Override
        public String convertToDatabaseValue(OutputFormat entityProperty) {
            return entityProperty.name();
        }
    }
}
