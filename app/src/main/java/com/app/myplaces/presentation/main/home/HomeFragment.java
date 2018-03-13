package com.app.myplaces.presentation.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.presentation.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment implements HomeContract.View {


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
    public void showLocationList(HomeAdapter adapter) {
        //TODO
    }
}
