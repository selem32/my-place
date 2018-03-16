package com.app.myplaces.presentation.detail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.Constants;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.presentation.base.BaseActivity;
import com.app.myplaces.presentation.custom.CustomStar;
import com.app.myplaces.service.model.location.LocationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.imageview_location_image)
    ImageView mImageViewImage;
    @BindView(R.id.recyclerview_detail_photos)
    RecyclerView mRecyclerViewPhotos;
    @BindView(R.id.textview_detail_about)
    TextView mTextviewAbout;
    @BindView(R.id.custom_star_detail_review)
    CustomStar mCustomStarReview;


    private DetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        configCollapsing();
        LocationItem locationItem = getIntent().getExtras().getParcelable(Constants.ARGUMENT_LOCATION_ITEM);

        mRecyclerViewPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        new DetailPresenter(this, this, locationItem);
        mPresenter.start();
    }


    private void configCollapsing() {
        mCollapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        mCollapsingToolbar.setCollapsedTitleTypeface(tf);
        mCollapsingToolbar.setExpandedTitleTypeface(tf);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }


    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(OperationError error) {
        //TODO
    }

    @Override
    public void showLoading(boolean isLoading) {
        //TODO
    }

    @Override
    public void configHeader(String title, double review, Bitmap image) {
        mCollapsingToolbar.setTitle(title);
        mImageViewImage.setImageBitmap(image);
    }

    @Override
    public void configReview(double review) {
        mCustomStarReview.configStars(review);
    }

    @Override
    public void configPhotoList(DetailPhotoAdapter adapter) {
        mRecyclerViewPhotos.setAdapter(adapter);
    }

    @Override
    public void configAbout(String source) {
        mTextviewAbout.setText(source);
    }

    public static Intent getLaunchIntent(Activity activity, LocationItem locationItem) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(Constants.ARGUMENT_LOCATION_ITEM, locationItem);
        return intent;
    }


}
