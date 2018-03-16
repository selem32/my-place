package com.app.myplaces.presentation.main.home;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.myplaces.R;
import com.app.myplaces.intrastructure.util.ConverterUtil;
import com.app.myplaces.presentation.custom.CustomStar;
import com.app.myplaces.service.model.location.Location;
import com.app.myplaces.service.model.location.LocationItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_HOLDER_BIG = 0;
    public static final int VIEW_HOLDER_SMALL = 1;
    private Context mContext;
    private HomePresenter.OnItemSelected mListerner;
    private Location mLocation;
    private int[] placeHolder = new int[]{R.color.duck_egg_blue, R.color.light_pink, R.color.creme};
    private int positionPlaceHolder;

    public HomeAdapter(Context context, Location location, HomePresenter.OnItemSelected listerner) {
        this.mContext = context;
        this.mListerner = listerner;
        this.mLocation = location;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_HOLDER_BIG:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_location_big, parent, false);
                return new ItemBigViewHolder(view);
            case VIEW_HOLDER_SMALL:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_location_small, parent, false);
                return new ItemSmallViewHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemBigViewHolder) {
            configItemBig((ItemBigViewHolder) holder, position);
        } else if (holder instanceof ItemSmallViewHolder) {
            configItemSmall((ItemSmallViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mLocation.getListLocations().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return VIEW_HOLDER_BIG;
        } else {
            return VIEW_HOLDER_SMALL;
        }
    }

    private void configItemBig(ItemBigViewHolder holder, int position) {
        LocationItem locationItem = mLocation.getListLocations().get(position);
        configImage(holder.mImageviewItemLocationImage, locationItem, R.dimen.item_location_big_image);
        setNameAndType(holder.mTextviewItemLocationName, holder.mTextviewItemLocationType,
                holder.mCustomStar, locationItem);
    }

    private void configItemSmall(ItemSmallViewHolder holder, int position) {
        LocationItem locationItem = mLocation.getListLocations().get(position);
        configImage(holder.mImageviewItemLocationImage, locationItem, R.dimen.item_location_big_image);
        setNameAndType(holder.mTextviewItemLocationName, holder.mTextviewItemLocationType,
                holder.mCustomStar, locationItem);
    }

    private void setNameAndType(TextView name, TextView type, CustomStar customStar, LocationItem locationItem) {
        name.setText(locationItem.getName());
        type.setText(locationItem.getType());
        customStar.configStars(locationItem.getReview());
    }

    private void configImage(ImageView imageView, LocationItem locationItem, int resourceId) {
        if (positionPlaceHolder >= placeHolder.length) {
            positionPlaceHolder = 0;
        }

        Picasso.get()
                .load(locationItem.getUrlImage())
                .placeholder(placeHolder[positionPlaceHolder])
                .resize(ConverterUtil.getPxFromDp(mContext, R.dimen.item_location_widht),
                        ConverterUtil.getPxFromDp(mContext, resourceId))
                .centerCrop()
                .into(imageView);

        positionPlaceHolder++;
    }

    class ItemBigViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_location_image)
        ImageView mImageviewItemLocationImage;
        @BindView(R.id.textview_item_location_name)
        TextView mTextviewItemLocationName;
        @BindView(R.id.textview_item_location_type)
        TextView mTextviewItemLocationType;
        @BindView(R.id.cardview_item_service_list_main_content)
        CardView mCardviewItemServiceListMainContent;
        @BindView(R.id.item_location_custom_star)
        CustomStar mCustomStar;

        ItemBigViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mCardviewItemServiceListMainContent.setOnClickListener(view1 -> {
                mListerner.onItemClick(mLocation.getListLocations().get(getAdapterPosition()), mImageviewItemLocationImage);
            });
        }
    }

    class ItemSmallViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_location_image)
        ImageView mImageviewItemLocationImage;
        @BindView(R.id.textview_item_location_name)
        TextView mTextviewItemLocationName;
        @BindView(R.id.textview_item_location_type)
        TextView mTextviewItemLocationType;
        @BindView(R.id.cardview_item_service_list_main_content)
        CardView mCardviewItemServiceListMainContent;
        @BindView(R.id.item_location_custom_star)
        CustomStar mCustomStar;

        ItemSmallViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mCardviewItemServiceListMainContent.setOnClickListener(view1 -> {
                mListerner.onItemClick(mLocation.getListLocations().get(getAdapterPosition()), mImageviewItemLocationImage);
            });
        }
    }
}
