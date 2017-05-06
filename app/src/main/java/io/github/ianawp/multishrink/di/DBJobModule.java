package io.github.ianawp.multishrink.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.ianawp.multishrink.store.DummyProcessor;
import io.github.ianawp.multishrink.store.ImageProcessor;
import io.github.ianawp.multishrink.store.db.JobPersistenceManager;
import io.github.ianawp.multishrink.store.fs.FSPersistenceManager;

/**
 * Created by IanAWP on 5/05/2017.
 */
@Module
public class DBJobModule {

    @Singleton @Provides
    static JobPersistenceManager providePersistenceManger(FSPersistenceManager fsPersistenceManager){
        return fsPersistenceManager;
    }

    @Singleton @Provides
    static ImageProcessor provideImageProcessor(DummyProcessor dummyProcessor){
        return dummyProcessor;
    }
}
