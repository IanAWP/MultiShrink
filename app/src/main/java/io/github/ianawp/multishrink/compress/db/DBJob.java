package io.github.ianawp.multishrink.compress.db;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

import io.github.ianawp.multishrink.compress.Job;
import io.github.ianawp.multishrink.compress.JobStatus;
import io.github.ianawp.multishrink.compress.OutputFormat;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by IanAWP on 21/04/2017.
 */

@Entity(
        nameInDb = "JOB"
)
public class DBJob implements Job {
    @Id(autoincrement = true)
    @Property( nameInDb = "ID_JOB")
    private Long id;

    @NotNull
    @Convert(converter = OutputFormatConverter.class, columnType = String.class)
    private  OutputFormat format;

    @NotNull
    private  int resolution;
    @ToMany(referencedJoinProperty = "jobID")
    private List<DBImage> images;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 724773420)
    private transient DBJobDao myDao;

    @Generated(hash = 946533249)
    public DBJob(Long id, @NotNull OutputFormat format, int resolution) {
        this.id = id;
        this.format = format;
        this.resolution = resolution;
    }

    @Generated(hash = 162743047)
    public DBJob() {
    }

    @Override
    public List<String> getJobList() {
        ArrayList<String> imageSet = new ArrayList<String>();
        for (DBImage im:getImages()) {
            imageSet.add(im.getUri());
        }
        return imageSet;
    }

    @Override
    public JobStatus getStatus() {
        return null;
    }

    @Override
    public void setStatus(JobStatus status) {

    }


    @Override
    public String getKey() {
        return id.toString();
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

    public Long getId() {
        return this.id;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 338548545)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDBJobDao() : null;
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
