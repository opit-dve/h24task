package com.example.home24task.rest;


import com.example.home24task.model.ArticleEnvelope;

import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;
import rx.Observable;

public interface ApiService {


    @GET("/articles?appDomain=1")
    Observable<ArticleEnvelope> getArticles(@Header("Accept-Language") String acceptLanguage,
                                         @Query("limit") int limit);
}
