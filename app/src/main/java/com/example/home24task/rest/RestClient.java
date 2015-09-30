package com.example.home24task.rest;

import com.example.home24task.app.Config;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestClient {


    private static ApiService sApiService;

    private RestClient() {}

    private static  RestAdapter getRestAdapter() {

        Gson gson = new GsonBuilder()
                //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        /*RequestInterceptor requestInterceptor = new RequestInterceptor()  {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam(Parameters.PARAM_API_KEY, Config.ADFLY_API_KEY);
                request.addQueryParam(Parameters.PARAM_USER_ID, Config.ADFLY_USER_ID);
            }
        };*/

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Config.WS_BASE_URL)
                .setConverter(new GsonConverter(gson))
                //.setRequestInterceptor(requestInterceptor)
                .build();

    }

    public static ApiService getApiService() {

        if(sApiService == null){

            sApiService = getRestAdapter().create(ApiService.class);
        }

        return sApiService;
    }
}
