package com.app.myplaces.presentation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.myplaces.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CustomStar extends LinearLayout {
    public static final String CUSTOM_STAR = "CUSTOM_STAR";
    @BindView(R.id.custom_star_one)
    ImageView mCustomStarOne;
    @BindView(R.id.custom_star_two)
    ImageView mCustomStarTwo;
    @BindView(R.id.custom_star_three)
    ImageView mCustomStarThree;
    @BindView(R.id.custom_star_fourt)
    ImageView mCustomStarFourt;
    @BindView(R.id.custom_star_five)
    ImageView mCustomStarFive;
    @BindView(R.id.custom_star_review)
    TextView mCustomStarReview;

    private Unbinder mUnbinder;

    public CustomStar(Context context) {
        super(context);
        initView(context, null);
    }

    public CustomStar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomStar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.custom_stars, this);
        ButterKnife.bind(this, view);
        parseAttrs(context, attrs);
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomStar,
                0, 0);

        try {
            int marginRight = typedArray.getDimensionPixelOffset(R.styleable.CustomStar_star_margin_right,
                    context.getResources().getDimensionPixelOffset(R.dimen.custom_star_default_margin));

            int textSize = typedArray.getDimensionPixelOffset(R.styleable.CustomStar_star_review_text_size,
                    context.getResources().getDimensionPixelOffset(R.dimen.text_size_small));

            int widht = typedArray.getDimensionPixelOffset(R.styleable.CustomStar_star_width,
                    context.getResources().getDimensionPixelOffset(R.dimen.item_location_star));

            int height = typedArray.getDimensionPixelOffset(R.styleable.CustomStar_star_height,
                    context.getResources().getDimensionPixelOffset(R.dimen.item_location_star));

            int color = typedArray.getColor(R.styleable.CustomStar_star_review_text_color,
                    ContextCompat.getColor(context, android.R.color.black));

            mCustomStarReview.setTextColor(color);
            mCustomStarReview.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            adjustMarginStar(marginRight, widht, height);

        } catch (Exception e) {
            Log.e(CUSTOM_STAR,
                    getClass().getSimpleName() + ".parseAttrs", e);
        } finally {
            typedArray.recycle();
        }
    }

    private void adjustMarginStar(int marginRight, int width, int height) {
        LayoutParams margins = new LayoutParams(width, height);
        margins.rightMargin = marginRight;
        mCustomStarOne.setLayoutParams(margins);
        mCustomStarTwo.setLayoutParams(margins);
        mCustomStarThree.setLayoutParams(margins);
        mCustomStarFourt.setLayoutParams(margins);
        mCustomStarFive.setLayoutParams(margins);
    }

    public void configStars(double review) {
        mCustomStarReview.setText(String.format(Locale.US, "%.2f", review));

        if (review >= 0.5
                && review < 1.5) {
            mCustomStarOne.setActivated(true);
        } else if (review >= 1.5
                && review < 2.5) {
            mCustomStarOne.setActivated(true);
            mCustomStarTwo.setActivated(true);
        } else if (review >= 2.5
                && review < 3.5) {
            mCustomStarOne.setActivated(true);
            mCustomStarTwo.setActivated(true);
            mCustomStarThree.setActivated(true);
        } else if (review >= 3.5
                && review < 4.5) {
            mCustomStarOne.setActivated(true);
            mCustomStarTwo.setActivated(true);
            mCustomStarThree.setActivated(true);
            mCustomStarFourt.setActivated(true);
        } else {
            mCustomStarOne.setActivated(true);
            mCustomStarTwo.setActivated(true);
            mCustomStarThree.setActivated(true);
            mCustomStarFourt.setActivated(true);
            mCustomStarFive.setActivated(true);
        }
    }
}
