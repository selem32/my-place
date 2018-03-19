package com.app.myplaces.presentation.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.error.OperationError;
import com.app.myplaces.presentation.dialog.ErrorDialogFragment;
import com.app.myplaces.presentation.dialog.ProgressDialogFragment;

import static com.google.common.base.Preconditions.checkNotNull;

public class BaseActivity extends AppCompatActivity {
    private static final String FRAGMENT_ALERT_ID = "FRAGMENT_ALERT_ID";
    private ProgressDialogFragment mProgressDialog;
    private ErrorDialogFragment errorDialog;

    public void showError(OperationError error, ErrorDialogFragment.MyOnErrorClick myOnErrorClick) {
        errorDialog = ErrorDialogFragment.newInstance(getErrorMsg(error));
        errorDialog.setListener(myOnErrorClick);
        errorDialog.show(getSupportFragmentManager(), FRAGMENT_ALERT_ID);
    }

    private String getErrorMsg(OperationError error) {

        switch (error.getErrorType()) {
            case NETWORK_ERROR:
                return getString(R.string.default_network_error);
            case SERVICE_ERROR:
                return getString(R.string.service_error);
            case GENERIC_ERROR:
            default:
                return getString(R.string.generic_error);
        }
    }

    public void showProgress() {
        mProgressDialog = new ProgressDialogFragment();
        mProgressDialog.show(getSupportFragmentManager(), FRAGMENT_ALERT_ID);
    }


    public void hideProgress() {
        mProgressDialog.dismiss();
    }

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
