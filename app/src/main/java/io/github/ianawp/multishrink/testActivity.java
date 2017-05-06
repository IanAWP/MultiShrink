package io.github.ianawp.multishrink;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.github.ianawp.multishrink.common.JobAdapter;
import io.github.ianawp.multishrink.di.DaggerTestActivityComponent;
import io.github.ianawp.multishrink.di.TestActivityComponent;
import io.github.ianawp.multishrink.di.TestActivityModule;
import io.github.ianawp.multishrink.store.JobManager;

public class testActivity extends AppCompatActivity {

    static class ContentView{
     @BindView(R.id.rvJobs)
      RecyclerView recyclerView;

    }



    @BindView(R.id.tstContent)
    View contentView;

    @Inject
    JobManager jobManager;

    @Inject
   JobAdapter mAdapter;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    RecyclerView recyclerView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doInject();


     // lveMain = (ExpandableListView)findViewById(R.id.lveMain);

        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);
        ContentView v = new ContentView();
        ButterKnife.bind(v,contentView);
//        this.lveMain = v.lveMain;
        this.recyclerView = v.recyclerView;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        UpdateList();



    }

    private void doInject() {
              DaggerTestActivityComponent.builder().applicationComponent( App
                .getAppComponent())
                .testActivityModule(new TestActivityModule(this))
                .build()
                .inject(this);
    }

    private boolean resumeHasRun = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (!resumeHasRun) {
            resumeHasRun = true;
            return;
        }
        UpdateList();
        // Normal case behavior follows
    }

    private void UpdateList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

}
