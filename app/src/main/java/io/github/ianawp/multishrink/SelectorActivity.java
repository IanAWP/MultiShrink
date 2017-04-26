package io.github.ianawp.multishrink;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ianawp.multishrink.compress.JobManager;
import io.github.ianawp.multishrink.compress.OutputFormat;
import io.github.ianawp.multishrink.di.AppModule;
import io.github.ianawp.multishrink.di.DaggerApplicationComponent;

public class SelectorActivity extends AppCompatActivity {
    String TAG="SELECTOR ACTIVITY";
    @Inject
    JobManager jobManager;
    @Inject
    SharedPreferences prefs;

    @BindView(R.id.spnRes) Spinner spnRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getApplication()).getAppComponent().inject(this);
        Log.i(TAG, jobManager.toString());
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_selector);
        ButterKnife.bind(this);
        setUpSpinner();
        handelIncomingIntent();
    }

    private void handelIncomingIntent() {
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
             if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // Handle multiple images being sent
            }
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            ArrayList<Uri> uris = new ArrayList<Uri>();
            SaveNewJob(uris);
        }
    }

    private void SaveNewJob(ArrayList<Uri> uris) {
       String jID = jobManager.createJob(uris, 1200, OutputFormat.SAME_AS_INPUT);
        Log.i(TAG, String.format("create new job [%s]" , jID));
    }


    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            SaveNewJob(imageUris);
        }
    }

    private void setUpSpinner() {
        //captions from settings
       String[] res = getResources().getStringArray(R.array.pref_image_size_names);
//values from settings
        String[] vals = getResources().getStringArray(R.array.pref_image_size_values);
        //selected value from settings
        String selected = prefs.getString(getString(R.string.key_images_output_size),"");
        int index = 0;
        for(int i = 0; i< vals.length;i++){
            if(vals[i].equalsIgnoreCase(selected)){
                index = i;
            }
        }

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, res);


        spnRes.setAdapter(adapter);
        spnRes.setSelection(index, true);
    }

}
