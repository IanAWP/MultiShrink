package io.github.ianawp.multishrink;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectorActivity extends AppCompatActivity {

    @BindView(R.id.spnRes) Spinner spnRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_selector);
        ButterKnife.bind(this);
        setUpSpinner();
    }

    private void setUpSpinner() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("120x120");
        list.add("320x240");
        list.add("640x480");
        list.add("800x600");
        list.add("1024x768");
        list.add("1280x960");
        list.add("1536x1180");
        list.add("1600x1200");
        list.add("2048x1536");

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);

        spnRes.setAdapter(adapter);
    }

}
