package com.walter.cojal.easylearning.network;

import com.walter.cojal.easylearning.data.Entities.Result;
import com.walter.cojal.easylearning.data.Entities.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceApi {

    @GET("initData")
    Observable<Result> getInitData();

    @POST("login")
    @FormUrlEncoded
    Observable<Result> login(@Field("email") String email,
                       @Field("password") String password,
                       @Field("token") String token);

    @POST("register")
    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    Observable<Result> signup(@Body User user);

    @POST("email")
    @FormUrlEncoded
    Observable<Result> sendEmail(@Field("email") String email);

    @POST("validatePhone")
    @FormUrlEncoded
    Observable<Result> validatePhone(@Field("phone") String phone);

    @GET("getHomeData")
    Observable<Result> getHomeData();

}
