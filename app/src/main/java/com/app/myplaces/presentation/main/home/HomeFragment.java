package com.app.myplaces.presentation.main.home;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.intrastructure.util.ImageExtraUtil;
import com.app.myplaces.intrastructure.util.ScreenUtil;
import com.app.myplaces.presentation.base.BaseFragment;
import com.app.myplaces.presentation.detail.DetailActivity;
import com.app.myplaces.service.model.location.LocationItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private final static int NUMBER_OF_COLUMNS = 2;
    private final static int ORIENTATION = 1;

    @BindView(R.id.recyclerview_item_list)
    RecyclerView mRecyclerviewItemList;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.linearlayout_home_main_content)
    LinearLayout mLinearlayoutMainContent;
    @BindView(R.id.view_home_status)
    View mViewStatus;
    @BindView(R.id.toolbar_container)
    AppBarLayout mToolbarContainer;
    private Unbinder mUnbinder;
    private HomeContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        configViewSize();
        mToolbar.setTitle(R.string.home_title);
        configRecyclerView();
        return view;
    }

    private void configViewSize() {
        ViewGroup.LayoutParams params = mViewStatus.getLayoutParams();
        params.height = ScreenUtil.getStatusBarHeight(getContext());
        mViewStatus.setLayoutParams(params);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
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
    public void showDetail(LocationItem locationItem, ImageView view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view,
                ViewCompat.getTransitionName(view));

        ImageExtraUtil.getInstance().setImageLocation(((BitmapDrawable) view.getDrawable()).getBitmap());
        startActivity(DetailActivity.getLaunchIntent(getActivity(), locationItem), options.toBundle());
    }

    @Override
    public void showLocationList(HomeAdapter adapter) {
        if (mRecyclerviewItemList != null) {
            mRecyclerviewItemList.setAdapter(adapter);
        }
    }

    private void configRecyclerView() {
        mRecyclerviewItemList.setLayoutManager(new StaggeredGridLayoutManager(NUMBER_OF_COLUMNS, ORIENTATION));
    }
}
