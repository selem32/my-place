package com.app.myplaces.presentation.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.myplaces.service.model.image.ImageItem;

import java.util.List;

public class DetailPhotoPageAdapter extends FragmentStatePagerAdapter {
    private List<ImageItem> mImageItemList;
    private DetailActivity.OnPhotoClickListener mCallback;

    public DetailPhotoPageAdapter(FragmentManager fm, List<ImageItem> imageItemList, DetailActivity.OnPhotoClickListener callback) {
        super(fm);
        this.mImageItemList = imageItemList;
        this.mCallback = callback;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailPhotoFragment.newInstance(mImageItemList.get(position), mCallback);
    }

    @Override
    public int getCount() {
        return mImageItemList.size();
    }

    @Override
    public float getPageWidth(int position) {
        return 0.3f;
    }
}
