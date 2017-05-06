package io.github.ianawp.multishrink.di;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import io.github.ianawp.multishrink.common.JobAdapter;
import io.github.ianawp.multishrink.store.JobManager;

/**
 * Created by IanAWP on 3/05/2017.
 */

@Module
public class TestActivityModule {
    Activity mActivity;
    public TestActivityModule(Activity activity){
        mActivity=activity;

    }

    @Provides
    RecyclerView.LayoutManager provideLayoutManager(){
        return new GridLayoutManager(mActivity, 2);
    }


    @Provides
    Activity provideActivity(){
        return mActivity;
    }
}
