package io.github.ianawp.multishrink.di;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import javax.inject.Scope;

import dagger.Component;
import io.github.ianawp.multishrink.testActivity;

/**
 * Created by IanAWP on 3/05/2017.
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = TestActivityModule.class
)
public interface TestActivityComponent {

    void inject(testActivity activity);
}

