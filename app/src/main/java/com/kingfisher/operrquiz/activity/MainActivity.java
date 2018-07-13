package com.kingfisher.operrquiz.activity;

import android.os.Bundle;

import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import com.kingfisher.operrquiz.BuildConfig;
import com.kingfisher.operrquiz.R;
import com.kingfisher.operrquiz.fragment.RestaurantMapFragment;

import timber.log.Timber;

public class MainActivity extends RequestPermissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesManager.init(this, true);
        setContentView(R.layout.activity_main);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameContainer, new RestaurantMapFragment())
                .commit();

        showTest();

    }

    private void showTest(){
        // This is a test function which should be tested only
    }
}
