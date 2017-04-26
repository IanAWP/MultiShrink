package io.github.ianawp.multishrink;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ianawp.multishrink.common.ExpandableListAdapter;
import io.github.ianawp.multishrink.compress.Job;
import io.github.ianawp.multishrink.compress.JobManager;

public class testActivity extends AppCompatActivity {

    static class ContentView{
        @BindView(R.id.lveMain)
        ExpandableListView lveMain;
    }

    @BindView(R.id.tstContent)
    View contentView;

    @Inject
    JobManager jobManager;


    ExpandableListView lveMain;

    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getApplication()).getAppComponent().inject(this);
     // lveMain = (ExpandableListView)findViewById(R.id.lveMain);

        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);
        ContentView v = new ContentView();
        ButterKnife.bind(v,contentView);
        this.lveMain = v.lveMain;
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
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, jobManager.getAllJobs());

        // setting list adapter
        lveMain.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<Job> jobs= jobManager.getAllJobs();

        for (Job j:jobs ) {
            listDataHeader.add(j.getKey());
            listDataChild.put(j.getKey(), j.getJobList());
        }

    }

}
