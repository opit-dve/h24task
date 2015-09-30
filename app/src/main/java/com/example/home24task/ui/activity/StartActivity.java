package com.example.home24task.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.home24task.R;
import com.example.home24task.app.Config;
import com.example.home24task.model.ArticleEnvelope;
import com.example.home24task.rest.RestClient;

import java.util.HashSet;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.app.AppObservable;
import rx.functions.Action1;
import timber.log.Timber;

public class StartActivity extends BaseActivity {

    @Bind(R.id.progress) ProgressBar mPbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(StartActivity.this);
    }

    @OnClick(R.id.btn_start)
    public void start(View view) {

        if (mPbLoading.getVisibility() != View.VISIBLE) {
            mPbLoading.setVisibility(View.VISIBLE);
            loadArticles(Config.DEFAULT_LANGUAGE, 10);
        }
    }

    private void loadArticles(final String acceptLanguage, final int limit) {

        mCompositeSubscription.add(
                AppObservable.bindActivity(StartActivity.this,
                        RestClient.getApiService().getArticles(acceptLanguage, limit))
                        .subscribe(
                                // success
                                new Action1<ArticleEnvelope>() {
                                    @Override
                                    public void call(ArticleEnvelope articleEnvelope) {

                                        mPbLoading.setVisibility(View.GONE);
                                        processArticleResult(articleEnvelope);
                                    }
                                },
                                // fail
                                new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {

                                        mPbLoading.setVisibility(View.GONE);
                                        Toast.makeText(StartActivity.this,
                                                getString(R.string.error_loading_articles),
                                                Toast.LENGTH_LONG).show();
                                        Timber.e("Error loading articles: " + throwable.getMessage());
                                    }
                                }
                        )
        );
    }

    private void processArticleResult(ArticleEnvelope articleEnvelope) {

        if (articleEnvelope.embedded != null
                && articleEnvelope.embedded.articles != null
                && articleEnvelope.embedded.articles.size() > 0) {

            mApplication.setArticles(articleEnvelope.embedded.articles);
            mApplication.setLikedArticleSkus(new HashSet<String>());
            startSingleActivity(SelectionActivity.class);
        }
        else {
            Toast.makeText(StartActivity.this,
                    getString(R.string.error_loading_articles),
                    Toast.LENGTH_LONG).show();
        }
    }
}
