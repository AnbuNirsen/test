package com.example.test.network;

import com.example.test.model.TestJsonResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;



public interface RetrofitInterface {
    @GET("/iranjith4/ad-assignment/db")
    Observable<Response<TestJsonResponse>> getJsonResponse();


}
