package io.github.ianawp.multishrink;

import android.app.Application;

import io.github.ianawp.multishrink.di.AppModule;
import io.github.ianawp.multishrink.di.ApplicationComponent;
import io.github.ianawp.multishrink.di.DaggerApplicationComponent;

/**
 * Created by IanAWP on 21/04/2017.
 */

public class App extends Application {

    private static ApplicationComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();

    }

    public static ApplicationComponent getAppComponent(){
        return sAppComponent;
    }
}
