package io.github.ianawp.multishrink.di;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import io.github.ianawp.multishrink.PreferenceActivity;
import io.github.ianawp.multishrink.SelectorActivity;
import io.github.ianawp.multishrink.store.ImageProcessor;
import io.github.ianawp.multishrink.store.JobManager;
import io.github.ianawp.multishrink.store.db.DaoSession;
import io.github.ianawp.multishrink.store.db.JobPersistenceManager;
import io.github.ianawp.multishrink.testActivity;


/**
 * Created by IanAWP on 21/04/2017.
 */
@Singleton
@Component(modules = {AppModule.class, DBJobModule.class})
public interface ApplicationComponent {
    Application getApplication();
    DaoSession getDAOSession();
//    SharedPreferences getSharedPreferences();
    JobManager getJobManager();
    ImageProcessor getProcessor();
    JobPersistenceManager getPersistenceManager();

    void inject(SelectorActivity activity);
    void inject(PreferenceActivity.SettingsFragment fragment);

}
