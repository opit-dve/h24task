package com.example.home24task.app;

import android.app.Application;

import com.example.home24task.BuildConfig;
import com.example.home24task.model.Article;

import java.util.ArrayList;
import java.util.HashSet;

import timber.log.Timber;

/**
 * Created by srd on 9/29/2015.
 */
public class Home24App extends Application {

    private ArrayList<Article> mArticles;
    private HashSet<String> mLikedArticleSkus;

    @Override
    public void onCreate() {
        super.onCreate();

        mArticles = new ArrayList<>();
        mLikedArticleSkus = new HashSet<>();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ArrayList<Article> getArticles() {
        return mArticles;
    }

    public void setArticles(ArrayList<Article> articles) {
        mArticles = articles;
    }

    public HashSet<String> getLikedArticleSkus() {
        return mLikedArticleSkus;
    }

    public void setLikedArticleSkus(HashSet<String> likedArticleSkus) {
        mLikedArticleSkus = likedArticleSkus;
    }
}
