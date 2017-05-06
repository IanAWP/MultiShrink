package io.github.ianawp.multishrink.store.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by IanAWP on 21/04/2017.
 */
@Entity( nameInDb = "IMAGES")
public class DBImage implements io.github.ianawp.multishrink.store.Image {
    @Id
    private Long id;
    @NotNull String imageName;
    private Long jobID;
    @NotNull
    private String sourceUri;

    private String targetUri;
    @NotNull
    private boolean isComplete = true;

    @Generated(hash = 1244515752)
    public DBImage(Long id, @NotNull String imageName, Long jobID,
            @NotNull String sourceUri, String targetUri, boolean isComplete) {
        this.id = id;
        this.imageName = imageName;
        this.jobID = jobID;
        this.sourceUri = sourceUri;
        this.targetUri = targetUri;
        this.isComplete = isComplete;
    }

    @Generated(hash = 839689711)
    public DBImage() {
    }

    @Override
    public String getSourceUri() {
        return sourceUri;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setSourceUri(String sourceUri) {
        this.sourceUri = sourceUri;
    }

    @Override
    public String getTargetUri() {
        return this.targetUri;
    }

    @Override
    public void setTargetUri(String targetUri) {
        this.targetUri = targetUri;
    }

    @Override
    public boolean getIsComplete() {
        return this.isComplete;
    }

    @Override
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Long getJobID() {
        return this.jobID;
    }

    public void setJobID(Long jobID) {
        this.jobID = jobID;
    }

    public String getImageName() {
        return this.imageName;
    }
@Override
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


}
