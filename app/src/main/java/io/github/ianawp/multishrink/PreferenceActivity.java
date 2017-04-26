package io.github.ianawp.multishrink;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.ExpandableListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ianawp.multishrink.di.AppModule;
import io.github.ianawp.multishrink.di.DaggerApplicationComponent;

public class PreferenceActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        setupActionBar();

    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Inject SharedPreferences prefs;

        android.preference.Preference.OnPreferenceChangeListener preferenceChangeListener = new android.preference.Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(android.preference.Preference preference, Object newValue) {
                if (preference instanceof ListPreference) {
                    // For list preferences, look up the correct display value in
                    // the preference's 'entries' list.
                    ListPreference listPreference = (ListPreference) preference;
                    int index = listPreference.findIndexOfValue(newValue.toString());

                    // Set the summary to reflect the new value.
                    preference.setSummary(
                            index >= 0
                                    ? listPreference.getEntries()[index]
                                    : null);

                }
                return true;
            }


        };

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ((App)getActivity().getApplication()).getAppComponent().inject(this);
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
            setUpPreferences();
        }

        private void setUpPreferences() {
            linkListValueToSummary(R.string.key_images_output_format);
            linkListValueToSummary(R.string.key_images_output_size);
            linkListValueToSummary(R.string.key_gallery_groupby);

        }

        private void linkListValueToSummary(int prefKey) {
            String sPref= getString(prefKey);
            android.preference.Preference pref = findPreference(sPref);
            pref.setOnPreferenceChangeListener(preferenceChangeListener);
            preferenceChangeListener.onPreferenceChange(pref,prefs.getString(sPref, "") );


        }


    }
}
