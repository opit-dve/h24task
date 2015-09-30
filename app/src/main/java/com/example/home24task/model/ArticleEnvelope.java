package com.example.home24task.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by srd on 9/29/2015.
 */
public class ArticleEnvelope {

    public long articlesCount;

    @SerializedName("_embedded")
    public Embedded embedded;


    public static class Embedded {

        public ArrayList<Article> articles;
    }
}
