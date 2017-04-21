package io.github.ianawp.multishrink.di;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import io.github.ianawp.multishrink.PreferenceActivity;
import io.github.ianawp.multishrink.SelectorActivity;
import io.github.ianawp.multishrink.compress.JobManager;
import io.github.ianawp.multishrink.compress.db.DaoSession;


/**
 * Created by IanAWP on 21/04/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface ApplicationComponent {
    Application getApplication();
    SharedPreferences getSharedPreferences();
    DaoSession getDAOSession();
    JobManager getJobManager();

    void inject(SelectorActivity activity);
    void inject(PreferenceActivity.SettingsFragment fragment);
}
