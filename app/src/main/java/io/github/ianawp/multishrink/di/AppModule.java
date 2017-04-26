package io.github.ianawp.multishrink.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.preference.PreferenceManager;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.ianawp.multishrink.compress.JobManager;
import io.github.ianawp.multishrink.compress.db.DBJobManager;
import io.github.ianawp.multishrink.compress.db.DaoMaster;
import io.github.ianawp.multishrink.compress.db.DaoSession;

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
    JobManager getJobManager(DaoSession ds){
        return new DBJobManager(ds);
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
}
