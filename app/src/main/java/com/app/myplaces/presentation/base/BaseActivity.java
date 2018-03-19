package com.app.myplaces.presentation.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.myplaces.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class BaseActivity extends AppCompatActivity {

    public void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                          @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    public Toolbar configToolbarDefault(Toolbar toolbar) {

        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            setSupportActionBar(toolbar);
            toolbar.bringToFront();
        }

        return toolbar;
    }
}
