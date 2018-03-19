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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.Constants;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.presentation.base.BaseActivity;
import com.app.myplaces.presentation.custom.CustomDetailInformation;
import com.app.myplaces.presentation.custom.CustomStar;
import com.app.myplaces.presentation.dialog.ErrorDialogFragment;
import com.app.myplaces.service.model.location.LocationItem;
import com.lusfold.spinnerloading.SpinnerLoading;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.custom_detail_information_time)
    CustomDetailInformation mCustomDetailInformationTime;
    @BindView(R.id.custom_detail_information_phone)
    CustomDetailInformation mCustomDetailInformationPhone;
    @BindView(R.id.custom_detail_information_location)
    CustomDetailInformation mCustomDetailInformationLocation;
    @BindView(R.id.recyclerview_detail_reviews)
    RecyclerView mRecyclerviewReviews;
    @BindView(R.id.textview_detail_review_qtde)
    TextView mTextviewReviewQtde;
    @BindView(R.id.spinner_detail)
    SpinnerLoading mSpinnerDetail;
    @BindView(R.id.linearlayout_detail_info_content)
    LinearLayout mLinearlayoutDetailInfoContent;


    private DetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        configCollapsing();
        LocationItem locationItem = getIntent().getExtras().getParcelable(Constants.ARGUMENT_LOCATION_ITEM);
        configToolbarDefault(mToolbar);

        mRecyclerViewPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerviewReviews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerviewReviews.setNestedScrollingEnabled(false);

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

            case R.id.menu_item_share:
                mPresenter.shareItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sharePlace(String about, String title, String location) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, title);
        share.putExtra(Intent.EXTRA_TEXT, String.format("%s %s %s", title, about, location));

        startActivity(Intent.createChooser(share, getString(R.string.detail_share_name)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        menu.findItem(R.id.menu_item_share);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(OperationError error) {
        showError(error, new ErrorDialogFragment.MyOnErrorClick() {
            @Override
            public void clickTryAgain() {
                mPresenter.callService();
            }

            @Override
            public void onBackPressed() {
                DetailActivity.this.onBackPressed();
            }
        });
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            mSpinnerDetail.setVisibility(View.VISIBLE);
            mLinearlayoutDetailInfoContent.setVisibility(View.GONE);
        } else {
            mSpinnerDetail.setVisibility(View.GONE);
            mLinearlayoutDetailInfoContent.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void configTime(String time) {
        mCustomDetailInformationTime.setText(time);
    }

    @Override
    public void configAddress(String location) {
        mCustomDetailInformationLocation.setText(location);
    }

    @Override
    public void configPhone(String phone) {
        mCustomDetailInformationPhone.setText(phone);
    }

    @Override
    public void configReviews(int qtde, DetailReviewAdapter adapter) {
        mRecyclerviewReviews.setAdapter(adapter);
        mTextviewReviewQtde.setText(String.format("ver todos os %d reviews", qtde));
    }

    public static Intent getLaunchIntent(Activity activity, LocationItem locationItem) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(Constants.ARGUMENT_LOCATION_ITEM, locationItem);
        return intent;
    }


    @OnClick({R.id.custom_detail_information_time, R.id.custom_detail_information_phone, R.id.custom_detail_information_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.custom_detail_information_time:
                //TODO
                break;
            case R.id.custom_detail_information_phone:
                //TODO
                break;
            case R.id.custom_detail_information_location:
                //TODO
                break;
        }
    }
}
