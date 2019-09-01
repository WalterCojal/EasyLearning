package com.walter.cojal.easylearning.data.network;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @Multipart
    @POST("setImage/{user_id}")
    Observable<Result> updateImage(@Part MultipartBody.Part media, @Path("user_id") int userId);

    @POST("email")
    @FormUrlEncoded
    Observable<Result> sendEmail(@Field("email") String email);

    @POST("validatePhone")
    @FormUrlEncoded
    Observable<Result> validatePhone(@Field("phone") String phone);

    @GET("getHomeData/{id}")
    Observable<Result> getHomeData(@Path("id") int userId);

    @Multipart
    @POST("addAssessor/{user_id}")
    Observable<Result> addAssessor(@Part("genre") RequestBody genre,
                                   @Part("document") RequestBody document,
                                   @Part("academic") RequestBody academic,
                                   @Part("assignments") RequestBody assignments,
                                   @Path("user_id") int userId);

    @GET("getAssessorData/{id}")
    Observable<Result> getAssessorData(@Path("id") int userId);

    @GET("getFavorites/{id}")
    Observable<Result> getFavorites(@Path("id") int userId);

    @POST("addFavorite")
    @FormUrlEncoded
    Observable<Result> addFavorite(@Field("user_id") int userId,
                                   @Field("assessor_id") int assessorId);

    @GET("getAssessorDetail/{id}")
    Observable<Result> getAssessorDetail(@Path("id") int assessorId);

    @GET("getAcademicList")
    Observable<Result> getLists();

}
