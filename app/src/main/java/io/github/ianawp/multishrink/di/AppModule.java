package io.github.ianawp.multishrink.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import org.greenrobot.greendao.database.Database;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.ianawp.multishrink.store.JobManager;
import io.github.ianawp.multishrink.store.db.DBJobManager;
import io.github.ianawp.multishrink.store.db.DaoMaster;
import io.github.ianawp.multishrink.store.db.DaoSession;

/**
 * Created by IanAWP on 21/04/2017.
 */
@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    JobManager getJobManager(DaoSession ds, Application a){
        return new DBJobManager(ds, a);
    }

    @Singleton
    @Provides
    SharedPreferences getSharedPreferences(Application app){
     return    PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Singleton
    @Provides
    DaoSession getDAOSession(Application app){

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app,"jobs-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        return  daoSession;
    }

    @Provides
    @Named("FSBase")
    File getFileBase(Application app){
        return app.getFilesDir();
    }
}
