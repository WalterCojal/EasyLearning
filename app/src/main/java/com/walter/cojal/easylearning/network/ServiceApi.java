package com.walter.cojal.easylearning.network;

import com.walter.cojal.easylearning.data.Entities.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {

    @GET("initData")
    Call<Result> getInitData();

}
