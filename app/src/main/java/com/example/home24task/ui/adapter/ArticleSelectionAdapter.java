package com.example.home24task.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.home24task.R;
import com.example.home24task.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srd on 9/30/2015.
 */
public class ArticleSelectionAdapter extends BaseAdapter {

    public interface ArticleSelectionAdapterCallbacks {

        void onArticleSelectionViewClicked(View view, int position);
    }

    private ArrayList<Article> mItems = new ArrayList<>();

    public ArticleSelectionAdapter(ArrayList<Article> items) {
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Article getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String getItemSku(int position) {
        Article article = getItem(position);
        return article != null ? article.sku : null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Context ctx = parent.getContext();
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater li = LayoutInflater.from(ctx);
            convertView = li.inflate(R.layout.adapter_item_article_selection, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }


        Article article = getItem(position);

        holder.title.setText(article.title);

        holder.image.setImageDrawable(ctx.getResources().getDrawable(R.mipmap.ic_launcher));
        if (article.media != null && article.media.size() > 0) {
            Picasso.with(ctx)
                    .load(article.media.get(0).uri)
                    .noFade()
                    .into(holder.image);
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_title) AppCompatTextView title;
        @Bind(R.id.image) ImageView image;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
