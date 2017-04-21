package io.github.ianawp.multishrink.compress.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by IanAWP on 21/04/2017.
 */
@Entity( nameInDb = "IMAGES")
public class DBImage{
    @Id
    private Long id;
    private Long jobID;
    @NotNull
    private String uri;

    @Generated(hash = 316727799)
    public DBImage(Long id, Long jobID, @NotNull String uri) {
        this.id = id;
        this.jobID = jobID;
        this.uri = uri;
    }

    @Generated(hash = 839689711)
    public DBImage() {
    }

    public String getUri() {
        return uri;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobID() {
        return this.jobID;
    }

    public void setJobID(Long jobID) {
        this.jobID = jobID;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


}
