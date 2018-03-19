package com.app.myplaces.presentation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.myplaces.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomDetailInformation extends LinearLayout {
    private static final String TAG = "CustomDetailInformation";

    @BindView(R.id.imageview_custom_detail_information_icon)
    ImageView mImageViewIcon;
    @BindView(R.id.textview_custom_detail_information_text)
    TextView mTextViewText;
    @BindView(R.id.linearlayout_custom_detail_information_main_content)
    LinearLayout mLinearLayoutMainContent;

    public CustomDetailInformation(Context context) {
        super(context);
        initView(context, null);
    }

    public CustomDetailInformation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomDetailInformation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.custom_detail_information, this);
        ButterKnife.bind(this, view);
        parseAttrs(context, attrs);
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomDetailInformation,
                0, 0);

        try {
            mImageViewIcon.setImageDrawable(typedArray.getDrawable(R.styleable.CustomDetailInformation_detail_icon));
        } catch (Exception e) {
            Log.e(TAG,
                    getClass().getSimpleName() + ".parseAttrs", e);
        } finally {
            typedArray.recycle();
        }
    }

    public void setIcon(Context context, int iconResId) {
        mImageViewIcon.setImageDrawable(ContextCompat.getDrawable(context, iconResId));
    }

    public void setText(String text) {
        this.mTextViewText.setText(text);
    }
}
