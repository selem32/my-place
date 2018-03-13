package com.app.myplaces.presentation.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.app.myplaces.R;
import com.app.myplaces.presentation.base.BaseActivity;
import com.app.myplaces.presentation.main.home.HomeFragment;
import com.app.myplaces.presentation.main.map.MapFragment;
import com.app.myplaces.presentation.main.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.imageview_bottom_bar_home)
    AppCompatImageView mBottomBarHome;
    @BindView(R.id.imageview_bottom_bar_map)
    AppCompatImageView mBottomBarMap;
    @BindView(R.id.imageview_bottom_bar_profile)
    AppCompatImageView mBottomBarProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        configBottomBarSelected(mBottomBarHome);
        inflateHomeFragment();
    }

    @OnClick({R.id.imageview_bottom_bar_home, R.id.imageview_bottom_bar_map, R.id.imageview_bottom_bar_profile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageview_bottom_bar_home:
                configBottomBarSelected(mBottomBarHome);
                inflateHomeFragment();
                break;
            case R.id.imageview_bottom_bar_map:
                configBottomBarSelected(mBottomBarMap);
                inflateMapFragment();
                break;
            case R.id.imageview_bottom_bar_profile:
                configBottomBarSelected(mBottomBarProfile);
                inflateProfileFragment();
                break;
        }
    }

    private void configBottomBarSelected(AppCompatImageView imageView) {
        mBottomBarHome.setSelected(false);
        mBottomBarMap.setSelected(false);
        mBottomBarProfile.setSelected(false);
        imageView.setSelected(true);
    }

    private void inflateHomeFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.framelayout_container);
        if (fragment instanceof HomeFragment) {
            return;
        }
        HomeFragment homeFragment = HomeFragment.newInstance();
        replaceFragmentToActivity(getSupportFragmentManager(), homeFragment, R.id.framelayout_container);
    }

    private void inflateMapFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.framelayout_container);
        if (fragment instanceof MapFragment) {
            return;
        }
        MapFragment mapFragment = MapFragment.newInstance();
        replaceFragmentToActivity(getSupportFragmentManager(), mapFragment, R.id.framelayout_container);
    }

    private void inflateProfileFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.framelayout_container);
        if (fragment instanceof ProfileFragment) {
            return;
        }
        ProfileFragment profileFragment = ProfileFragment.newInstance();
        replaceFragmentToActivity(getSupportFragmentManager(), profileFragment, R.id.framelayout_container);
    }
}
