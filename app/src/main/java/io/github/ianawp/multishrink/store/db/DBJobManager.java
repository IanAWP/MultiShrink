package io.github.ianawp.multishrink.store.db;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.github.ianawp.multishrink.common.URIConverter;
import io.github.ianawp.multishrink.store.Job;
import io.github.ianawp.multishrink.store.JobDescription;
import io.github.ianawp.multishrink.store.JobManager;
import io.github.ianawp.multishrink.store.OutputFormat;

/**
 * Created by IanAWP on 21/04/2017.
 */

public class DBJobManager implements JobManager {
    String TAG ="Job Manager";
    private final Application application;
    //private final JobProcessor processor;
    DaoSession daoSession;

    @Inject
    public DBJobManager(DaoSession ds, Application application){
        daoSession=ds;
        this.application = application;
      //  this.processor=processor;
    }

    @Override
    public Job getJobByID(String ID) {
      return   daoSession.getDBJobDao().queryBuilder().where(DBJobDao.Properties.Id.eq(ID)).unique();
    }

    @Override
    public String createJob(List<Uri> uris, int vres, OutputFormat outputFormat) {
        DBJobDescription j = new DBJobDescription();
        j.setResolution(vres);
        j.setFormat(outputFormat);
        j.setTimeStamp(new Date());
        daoSession.getDBJobDescriptionDao().insert(j);

        DBJob job = new DBJob();
        job.setJobDescription(j);
        daoSession.getDBJobDao().insert(job);

        for (Uri uri: uris             ) {
            DBImage image = new DBImage()  ;
            image.setJobID(job.getId());
            image.setSourceUri(uri.toString());
            image.setImageName(new File(uri.getPath()).getName());
            daoSession.getDBImageDao().insert(image);
        }
        return j.getId().toString();
    }



    @Override
    public  List<?extends Job> getJobs() {
        List<JobDescription> j = new ArrayList<JobDescription>();

    return (daoSession.getDBJobDao().queryBuilder().orderDesc(DBJobDao.Properties.Id).list());

    }

    @Override
    public void RemoveJob(Job job) {
     if(job!= null){
         job.deleteJob();

     }

    }

}
