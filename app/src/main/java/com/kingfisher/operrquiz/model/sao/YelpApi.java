package com.kingfisher.operrquiz.model.sao;

import com.kingfisher.operrquiz.model.data.BusinessResponse;
import com.kingfisher.operrquiz.model.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kingfisher on 6/17/17.
 */

public interface YelpApi {

    @FormUrlEncoded
    @Headers (ServiceConstants.HEADER_FORM_URLENCODED)
    @POST (ServiceConstants.URL_GET_TOKEN)
    Call<LoginResponse> getAccessToken(@Field ("grant_type") String grantType,
                                       @Field ("client_id") String clientId,
                                       @Field ("client_secret") String clientPass);

    @GET (ServiceConstants.URL_GET_BUSINESS)
    Call<BusinessResponse> getBusiness(@Header ("Authorization") String bearToken,
                                       @Query ("latitude") double lat,
                                       @Query ("longitude") double lng,
                                       @Query ("term") String term
    );

}


