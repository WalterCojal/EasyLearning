package com.walter.cojal.easylearning.network;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceApi {

    @GET("initData")
    Call<Result> getInitData();

    @POST("login")
    @FormUrlEncoded
    Call<Result> login(@Field("email") String email,
                       @Field("password") String password,
                       @Field("token") String token);

    @POST("signup")
    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    Call<Result> signup(@Body User user);

    @POST("email")
    @FormUrlEncoded
    Call<Result> sendEmail(@Field("email") String email);

    @POST("validatePhone")
    @FormUrlEncoded
    Call<Result> validatePhone(@Field("phone") String phone);

}
