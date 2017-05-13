package io.github.ianawp.multishrink;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.UriPermission;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ianawp.multishrink.store.Job;
import io.github.ianawp.multishrink.store.JobCallback;
import io.github.ianawp.multishrink.store.JobManager;
import io.github.ianawp.multishrink.store.OutputFormat;

public class SelectorActivity extends AppCompatActivity {
    String TAG="SELECTOR ACTIVITY";
    @Inject
    JobManager jobManager;
    @Inject
    SharedPreferences prefs;

    @BindView(R.id.spnRes) Spinner spnRes;
    @BindView(R.id.btnShrink)     Button btnShrink;
    @BindView(R.id.prgCount)    ProgressBar prgCount;
    @BindView(R.id.loExtra)     ConstraintLayout loExtra;
    @BindView(R.id.ibtnSettings)     ImageButton btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        Log.i(TAG, jobManager.toString());
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_selector);
        ButterKnife.bind(this);
        setVisibility();
        setUpSpinner();
        setUpHandler();
        handelIncomingIntent();
    }

    private void setVisibility() {
        prgCount.setVisibility(View.GONE);
        loExtra.setVisibility(View.GONE);
    }

    private void setUpHandler() {
        btnShrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prgCount.setVisibility(View.VISIBLE);
                prgCount.setIndeterminate(true);
                Job job = jobManager.getJobByID(getCurrentJob());
                job.getJobDescription().setMaxDimension(getDimen());
                prgCount.setMax(job.getAllImages().size());
                new RunJob().execute(job);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loExtra.getVisibility() == View.VISIBLE){
                    loExtra.setVisibility(View.GONE);
                }
                else{
                    loExtra.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private class RunJob extends AsyncTask<Job,Integer, Boolean>{


        @Override
        protected Boolean doInBackground(Job... params) {
            params[0].RunJob(new JobCallback() {
                @Override
                public void OnUpdate(int current, int total) {
                    publishProgress(current);
                }
            });
            return  true;
        }

        protected void onProgressUpdate(Integer... progress) {
            //prgCount.setIndeterminate(false);
           prgCount.setIndeterminate(false);
            prgCount.setProgress(progress[0]);

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            prgCount.setVisibility(View.GONE);
            prgCount.setProgress(0);

        }
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    private String currentJob = "-1";


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
        Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            ArrayList<Uri> uris = new ArrayList<>();
            uris.add(imageUri);
            SaveNewJob(uris);
        }
    }

    private void SaveNewJob(ArrayList<Uri> uris) {
        List<String> names = new ArrayList<>(uris.size());
        for (Uri uri:uris   ) {
            names.add(getNameFromURI(uri));
        }
        int dimen = getDimen();
        String jID = jobManager.createJob(uris, names, dimen,OutputFormat.SAME_AS_INPUT);
        setCurrentJob(jID);

        Log.i(TAG, String.format("create new job [%s]" , jID));
    }

    private int getDimen() {
        String[] vals = getResources().getStringArray(R.array.pref_image_size_values);
        String selectedValue = vals[spnRes.getSelectedItemPosition()];

        return Integer.parseInt(selectedValue);
    }


    String getNameFromURI(Uri uri) {
        String fileName=null;
        if (uri.getScheme().equals("file")) {
            fileName = uri.getLastPathSegment();
        } else {
            Cursor cursor = null;
            try {
                cursor = getContentResolver().query(uri, new String[]{
                        MediaStore.Images.ImageColumns.DISPLAY_NAME
                }, null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));
                    Log.d(TAG, "name is " + fileName);
                }

            }
            catch(Exception ex){
            ex.printStackTrace();
            }
            finally {

                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if(fileName == null){
            fileName = getRealPathFromURI(uri);
        }
        return fileName;


    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(App.getAppComponent().getApplication(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return new File(result).getName();
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
        ArrayAdapter<String> adapter =new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, res);


        spnRes.setAdapter(adapter);
        spnRes.setSelection(index, true);
    }

}
