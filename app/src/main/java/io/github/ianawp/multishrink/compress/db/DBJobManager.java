package io.github.ianawp.multishrink.compress.db;

import android.net.Uri;

import org.greenrobot.greendao.Property;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.ianawp.multishrink.App;
import io.github.ianawp.multishrink.compress.Job;
import io.github.ianawp.multishrink.compress.JobManager;
import io.github.ianawp.multishrink.compress.JobStatus;
import io.github.ianawp.multishrink.compress.OutputFormat;

/**
 * Created by IanAWP on 21/04/2017.
 */

public class DBJobManager implements JobManager {
        DaoSession daoSession;

    @Inject public DBJobManager(DaoSession ds){
        daoSession=ds;
    }

    @Override
    public Job getJobByID(String ID) {
      return   daoSession.getDBJobDao().queryBuilder().where(DBJobDao.Properties.Id.eq(ID)).unique();
    }

    @Override
    public String createJob(List<Uri> uris, int vres, OutputFormat outputFormat) {
        DBJob j = new DBJob();
        j.setResolution(vres);
        j.setFormat(outputFormat);
        daoSession.getDBJobDao().insert(j);

        for (Uri uri: uris             ) {
            DBImage image = new DBImage()  ;
            image.setJobID(j.getId());
            image.setUri(uri.toString());
            daoSession.getDBImageDao().insert(image);
        }
        return j.getKey();
    }



    @Override
    public List<Job> getAllJobs() {
        List<Job> j = new ArrayList<Job>();

        j.addAll(daoSession.getDBJobDao().queryBuilder().orderDesc(DBJobDao.Properties.Id).list());
        return j;
    }
}
