package com.app.myplaces.presentation.detail;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.util.ConverterUtil;
import com.app.myplaces.service.model.image.ImageItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private int[] placeHolder = new int[]{R.color.duck_egg_blue, R.color.light_pink, R.color.creme};
    private int positionPlaceHolder;
    private DetailPresenter.OnPhotoClickListener mListerner;
    private List<ImageItem> mImageItemList;

    public DetailPhotoAdapter(Context context, List<ImageItem> imageItemList, DetailPresenter.OnPhotoClickListener listerner) {
        this.mContext = context;
        this.mListerner = listerner;
        this.mImageItemList = imageItemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        configItem((ViewHolder) holder, mImageItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mImageItemList.size();
    }

    private void configItem(ViewHolder holder, ImageItem imageItem) {
        if (positionPlaceHolder >= placeHolder.length) {
            positionPlaceHolder = 0;
        }

        Picasso.get()
                .load(imageItem.getUrl())
                .placeholder(placeHolder[positionPlaceHolder])
                .resize(ConverterUtil.getPxFromDp(mContext, R.dimen.item_detail_photo_width),
                        ConverterUtil.getPxFromDp(mContext, R.dimen.item_detail_photo_height))
                .centerCrop()
                .into(holder.mImageViewPhoto);

        positionPlaceHolder++;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_item_detail_photo)
        ImageView mImageViewPhoto;
        @BindView(R.id.cardview_item_detail_photo_main_content)
        CardView mCardViewMainContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mCardViewMainContent.setOnClickListener(view1 -> {
                mListerner.onItemSelected(mImageItemList.get(getAdapterPosition()));
            });
        }
    }
}
