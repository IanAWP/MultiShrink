package io.github.ianawp.multishrink.compress.db;

import java.util.List;

import javax.inject.Inject;

import io.github.ianawp.multishrink.App;
import io.github.ianawp.multishrink.compress.Job;
import io.github.ianawp.multishrink.compress.JobManager;
import io.github.ianawp.multishrink.compress.JobStatus;

/**
 * Created by IanAWP on 21/04/2017.
 */

public class DBJobManager implements JobManager {
    @Inject
    DaoSession daoSession;

    @Override
    public Job getJobByID(String ID) {
      return   daoSession.getDBJobDao().queryBuilder().where(DBJobDao.Properties.Id.eq(ID)).unique();
    }


//    @Override
//    public List<Job> getJobByStatus(JobStatus status) {
//        appContext.getDaoSession().getDBJobDao().queryBuilder().where(DBJobDao.Properties,status).list().get(0);
//    }

    @Override
    public String createJob(Job job) {
//        DBJob job1 = new DBJob();
//        job1.setFormat(job.getFormat());
//        job1.setResolution(job.getResolution());
//        job1.set
//        appContext.getDaoSession().getDBJobDao().insert()
        return null;
    }
}
