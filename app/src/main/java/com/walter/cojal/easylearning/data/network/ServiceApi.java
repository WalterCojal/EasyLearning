package com.walter.cojal.easylearning.data.network;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    Observable<Result> signUp(@Body User user);

    @POST("userUpdate")
    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    Observable<Result> update(@Body User user);

    @POST("email")
    @FormUrlEncoded
    Observable<Result> sendEmail(@Field("email") String email);

    @POST("validatePhone")
    @FormUrlEncoded
    Observable<Result> validatePhone(@Field("phone") String phone);

    @GET("getHomeData")
    Observable<Result> getHomeData();

    @GET("getAssessorData/{id}")
    Observable<Result> getAssessorData(@Path("id") int userId);

}
