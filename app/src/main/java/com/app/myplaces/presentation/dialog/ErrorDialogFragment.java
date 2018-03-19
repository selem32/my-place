package com.app.myplaces.presentation.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.myplaces.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ErrorDialogFragment extends DialogFragment {
    private final static String KEY_ERROR_MSG = "KEY_ERROR_MSG";

    @BindView(R.id.textview_dialog_error_description)
    TextView mTextviewErrorDescription;

    private Unbinder unbinder;
    private MyOnErrorClick listener;

    public static ErrorDialogFragment newInstance(String errorMsg) {

        Bundle args = new Bundle();

        ErrorDialogFragment fragment = new ErrorDialogFragment();
        args.putString(KEY_ERROR_MSG, errorMsg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                if (listener != null) {
                    listener.onBackPressed();
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_erro, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configMsgError(getArguments().getString(KEY_ERROR_MSG));
    }

    private void configMsgError(String errorMsg) {
        mTextviewErrorDescription.setText(errorMsg);
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.error_button_try_again)
    public void clickTryAgain() {
        if (listener != null) {
            listener.clickTryAgain();
        }
    }

    public void setListener(MyOnErrorClick listener) {
        this.listener = listener;
    }

    public interface MyOnErrorClick {
        void clickTryAgain();

        void onBackPressed();
    }


}