package com.walter.cojal.easylearning.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {

    @GET("initData")
    Call<Integer> getInitData();

}
