package com.app.myplaces.presentation.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.myplaces.R;
import com.app.myplaces.presentation.custom.CustomStar;
import com.app.myplaces.service.model.review.ReviewItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fahmisdk6.avatarview.AvatarView;

public class DetailReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ReviewItem> mReviewItemList;

    public DetailReviewAdapter(Context context, List<ReviewItem> reviewItemList) {
        this.mContext = context;
        this.mReviewItemList = reviewItemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        configItem((ViewHolder) holder, mReviewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviewItemList.size();
    }

    private void configItem(ViewHolder holder, ReviewItem reviewItem) {
        holder.mItemReviewReview.configStars(reviewItem.getReview(), false);
        holder.mTextviewItemReviewTitle.setText(reviewItem.getTitle());
        holder.mTextviewItemReviewOpinion.setText(reviewItem.getOpinion());
        holder.mTextviewItemReviewName.setText(reviewItem.getUserInfo());
        holder.mImageviewItemReview.bind(reviewItem.getName(), "");
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_item_review)
        AvatarView mImageviewItemReview;
        @BindView(R.id.item_review_review)
        CustomStar mItemReviewReview;
        @BindView(R.id.textview_item_review_title)
        TextView mTextviewItemReviewTitle;
        @BindView(R.id.textview_item_review_opinion)
        TextView mTextviewItemReviewOpinion;
        @BindView(R.id.textview_item_review_name)
        TextView mTextviewItemReviewName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
