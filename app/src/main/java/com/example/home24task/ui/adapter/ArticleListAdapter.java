package com.example.home24task.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.home24task.R;
import com.example.home24task.model.Article;
import com.example.home24task.ui.fragment.ReviewFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srd on 10/1/2015.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {


    private ArrayList<Article> mItems = new ArrayList<>();
    private HashSet<String> mLikedArticleSkus = new HashSet<>();
    private ReviewFragment.LayoutManagerType mLayoutManagerType;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image) ImageView image;
        @Bind(R.id.tv_title) AppCompatTextView title;
        @Bind(R.id.iv_liked) ImageView liked;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(ArticleListAdapter.ViewHolder.this, itemView);
        }
    }

    public ArticleListAdapter(ArrayList<Article> items, HashSet<String> likedArticleSkus) {

        mItems = items;
        mLikedArticleSkus = likedArticleSkus;
    }

    public void setLayoutManagerType(ReviewFragment.LayoutManagerType layoutManagerType) {
        mLayoutManagerType = layoutManagerType;
    }

    @Override
    public ArticleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_article_list, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ArticleListAdapter.ViewHolder viewHolder, final int position) {

        Context ctx = viewHolder.itemView.getContext();
        Article item = mItems.get(position);

        // image
        viewHolder.image.setImageDrawable(ctx.getResources().getDrawable(R.mipmap.ic_launcher));
        if (item.media != null && item.media.size() > 0) {
            Picasso.with(ctx)
                    .load(item.media.get(0).uri)
                    .noFade()
                    .into(viewHolder.image);
        }

        viewHolder.title.setText(item.title);

        if (mLikedArticleSkus.contains(item.sku)) {
            viewHolder.liked.setVisibility(View.VISIBLE);
        }
        else {
            viewHolder.liked.setVisibility(View.GONE);
        }


        if (mLayoutManagerType == ReviewFragment.LayoutManagerType.GRID_LAYOUT_MANAGER) {
            viewHolder.title.setVisibility(View.GONE);
        }
        else {
            viewHolder.title.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}