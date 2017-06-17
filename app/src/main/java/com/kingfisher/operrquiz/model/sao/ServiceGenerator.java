package com.kingfisher.operrquiz.model.sao;

import com.google.gson.Gson;
import com.kingfisher.operrquiz.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kingfisher on 3/15/17.
 */

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(
                    BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));


    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ServiceConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new Gson()));

    public static Retrofit retrofit = builder.client(httpClient.build()).build();

    private static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private static YelpApi serviceApis = createService(YelpApi.class);

    public static YelpApi getYelpApi() {
        if (serviceApis == null) serviceApis = createService(YelpApi.class);
        return serviceApis;
    }

}
