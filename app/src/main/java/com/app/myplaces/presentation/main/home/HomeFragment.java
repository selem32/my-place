package com.app.myplaces.presentation.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.presentation.base.BaseFragment;
import com.app.myplaces.presentation.detail.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment implements HomeContract.View {


    @BindView(R.id.recyclerview_item_list)
    RecyclerView mRecyclerviewItemList;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private Unbinder mUnbinder;
    private HomeContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mToolbar.setTitle("Home");
        configRecyclerView();
        return view;
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
    public void showDetail(Pair<View, String> p1, Pair<android.view.View, String> p2) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
//        intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), p1, p2);
        startActivity(intent, options.toBundle());

    }

    @Override
    public void showLocationList(HomeAdapter adapter) {
        mRecyclerviewItemList.setAdapter(adapter);
    }

    private void configRecyclerView() {
        mRecyclerviewItemList.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
    }
}
