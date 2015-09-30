package com.example.home24task.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewAnimator;
import android.widget.LinearLayout;

import com.example.home24task.R;
import com.example.home24task.model.Article;
import com.example.home24task.ui.adapter.ArticleSelectionAdapter;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by srd on 9/30/2015.
 */
public class SelectionFragment extends BaseFragment {

    public interface SelectionFragmentCallbacks {
        void onSelectionFragmentViewClick(View v);
    }

    @Bind(R.id.article_view) AdapterViewAnimator mArticleView;
    @Bind(R.id.tv_likes_counter) AppCompatTextView mTvLikesCount;
    @Bind(R.id.btn_like) AppCompatButton mBtnLike;
    @Bind(R.id.btn_dislike) AppCompatButton mBtnDislike;
    @Bind(R.id.end_container) LinearLayout mEndContainer;

    private ArrayList<Article> mArticles;
    private HashSet<String> mLikedArticleSkus;
    private SelectionFragmentCallbacks mListener;

    private ArticleSelectionAdapter mAdapter;

    public static SelectionFragment newInstance() {

        Bundle args = new Bundle();

        SelectionFragment fragment = new SelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;

        if (context instanceof Activity){
            activity = (Activity) context;

            try {
                mListener = (SelectionFragmentCallbacks) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement SelectionFragmentCallbacks");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selection, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ButterKnife.bind(SelectionFragment.this, view);

        mArticles = mApplication.getArticles();
        mLikedArticleSkus = mApplication.getLikedArticleSkus();

        mArticleView.setAdapter(getQuizAdapter());
        mArticleView.setInAnimation(getActivity(), R.animator.slide_in_bottom);
        mArticleView.setOutAnimation(getActivity(), R.animator.slide_out_top);

        updateLikesCountUi();
    }

    private void updateLikesCountUi() {
        mTvLikesCount.setText(String.format("%d/%d",
                mLikedArticleSkus.size(),
                mArticles.size()));
    }

    private ArticleSelectionAdapter getQuizAdapter() {
        if (null == mAdapter) {
            mAdapter = new ArticleSelectionAdapter(mArticles);
        }
        return mAdapter;
    }

    public boolean showNextPage() {
        if (null == mArticleView) {
            return false;
        }

        int nextItem = mArticleView.getDisplayedChild() + 1;

        final int count = mArticleView.getAdapter().getCount();
        if (nextItem < count) {
            mArticleView.showNext();
            return true;
        }

        return false;
    }

    public void showEnd() {

        mEndContainer.setVisibility(View.VISIBLE);
        mArticleView.setVisibility(View.GONE);
        mBtnLike.setEnabled(false);
        mBtnDislike.setEnabled(false);
    }


    @OnClick(R.id.btn_like)
    public void onLike() {

        // logic to store liked SKUs
        int currentItem = mArticleView.getDisplayedChild();
        String currentSku = mAdapter.getItemSku(currentItem);
        if (currentSku != null) {
            mLikedArticleSkus.add(currentSku);
            updateLikesCountUi();
        }

        if (!showNextPage()) {
            showEnd();
        }
    }

    @OnClick(R.id.btn_dislike)
    public void onDislike() {
        if (!showNextPage()) {
            showEnd();
        }
    }

    @OnClick(R.id.btn_review)
    public void onReview(AppCompatButton button) {
        if (mListener != null) {
            mListener.onSelectionFragmentViewClick(button);
        }
    }

}
