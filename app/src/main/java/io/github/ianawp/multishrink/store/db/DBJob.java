package io.github.ianawp.multishrink.store.db;

import android.app.Application;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import io.github.ianawp.multishrink.App;
import io.github.ianawp.multishrink.di.DaggerApplicationComponent;
import io.github.ianawp.multishrink.di.DaggerDBJobComponent;
import io.github.ianawp.multishrink.store.Image;
import io.github.ianawp.multishrink.store.ImageProcessor;
import io.github.ianawp.multishrink.store.Job;
import io.github.ianawp.multishrink.store.JobDescription;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Transient;

import javax.inject.Inject;



/**
 * Created by IanAWP on 5/05/2017.
 */
@Entity
public class DBJob implements Job {
    @Inject
    @Transient
    ImageProcessor imageProcessor;
    @Inject
    @Transient
    JobPersistenceManager persistenceManager;
    @Transient
    static final  String TAG  = "DBJob";

    @Id(autoincrement = true)
    private Long id;
    
    private  boolean isComplete;
    private Long jobDescriptionID;

    @ToMany(referencedJoinProperty = "jobID")
    private List<DBImage> images;

    @ToOne(joinProperty = "jobDescriptionID")
    private DBJobDescription jobDescription;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 724773420)
    private transient DBJobDao myDao;

    @Keep
    public DBJob(Long id, boolean isComplete, Long jobDescriptionID) {
        this.id = id;
        this.isComplete = isComplete;
        this.jobDescriptionID = jobDescriptionID;
        inject();
    }

    private void inject() {
         DaggerDBJobComponent.builder().applicationComponent(App.getAppComponent()).build().Inject(this);
    }

    @Keep
    public DBJob() {
        inject();
    }

    @Generated(hash = 1522014938)
    private transient Long jobDescription__resolvedKey;

    @Override
    public void RunJob() {
            new JobRunner().execute(this);
    }

    public void ProcessImages() {
        for ( DBImage im:getImages()){
            OutputStream stream = null;
            try {
                stream = persistenceManager.getOutputStream(getId().toString(),im.getImageName());
            } catch (IOException e) {
                Log.d(TAG, e.toString());
            }
            imageProcessor.processImage(im, stream);

            im.setTargetUri(persistenceManager.getUri(getId().toString(),im.getImageName()).toString());
            daoSession.getDBImageDao().update(im);
        }
        this.setIsComplete(true);
    }

    @Override
    public List<? extends Image > getAllImages() {

        return getImages();
    }


    @Override
    public void deleteJob() {
        persistenceManager.deleteScope(this.getId().toString());
        this.delete();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobDescriptionID() {
        return this.jobDescriptionID;
    }

    public void setJobDescriptionID(Long jobDescriptionID) {
        this.jobDescriptionID = jobDescriptionID;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1496100368)
    public DBJobDescription getJobDescription() {
        Long __key = this.jobDescriptionID;
        if (jobDescription__resolvedKey == null
                || !jobDescription__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DBJobDescriptionDao targetDao = daoSession.getDBJobDescriptionDao();
            DBJobDescription jobDescriptionNew = targetDao.load(__key);
            synchronized (this) {
                jobDescription = jobDescriptionNew;
                jobDescription__resolvedKey = __key;
            }
        }
        return jobDescription;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 608114170)
    public void setJobDescription(DBJobDescription jobDescription) {
        synchronized (this) {
            this.jobDescription = jobDescription;
            jobDescriptionID = jobDescription == null ? null
                    : jobDescription.getId();
            jobDescription__resolvedKey = jobDescriptionID;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1054311647)
    public List<DBImage> getImages() {
        if (images == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DBImageDao targetDao = daoSession.getDBImageDao();
            List<DBImage> imagesNew = targetDao._queryDBJob_Images(id);
            synchronized (this) {
                if (images == null) {
                    images = imagesNew;
                }
            }
        }
        return images;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 604059028)
    public synchronized void resetImages() {
        images = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    
public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    
/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 338548545)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getDBJobDao() : null;
}

private class JobRunner extends AsyncTask<DBJob, Integer, Boolean>{
    @Override
    protected Boolean doInBackground(DBJob... params) {
       params[0].ProcessImages();
        return true;
    }
}
}
