package com.app.myplaces.presentation.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.Constants;
import com.app.myplaces.intrastructure.util.ConverterUtil;
import com.app.myplaces.presentation.base.BaseFragment;
import com.app.myplaces.service.model.image.ImageItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetailPhotoFragment extends BaseFragment {

    @BindView(R.id.imageview_item_detail_photo)
    ImageView mImageViewPhoto;
    private Unbinder mUnbinder;

    private int[] placeHolder = new int[]{R.color.duck_egg_blue, R.color.light_pink, R.color.creme};
    private int positionPlaceHolder;
    private ImageItem mImageItem;
    private static DetailActivity.OnPhotoClickListener mCallback;

    public static DetailPhotoFragment newInstance(ImageItem imageItem, DetailActivity.OnPhotoClickListener callback) {

        DetailPhotoFragment fragment = new DetailPhotoFragment();

        mCallback = callback;
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.ARGUMENT_IMAGE_ITEM, imageItem);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_detail_photo, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mImageItem = getArguments().getParcelable(Constants.ARGUMENT_IMAGE_ITEM);

        configImage();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void configImage() {
        if (positionPlaceHolder >= placeHolder.length) {
            positionPlaceHolder = 0;
        }

        Picasso.get()
                .load(mImageItem.getUrl())
                .placeholder(placeHolder[positionPlaceHolder])
                .resize(ConverterUtil.getPxFromDp(getContext(), R.dimen.item_detail_photo_width),
                        ConverterUtil.getPxFromDp(getContext(), R.dimen.item_detail_photo_height))
                .centerCrop()
                .into(mImageViewPhoto);

        positionPlaceHolder++;
    }

    @OnClick(R.id.cardview_item_detail_photo_main_content)
    public void onViewClicked() {
        mCallback.onItemSelected(mImageItem);
    }
}

