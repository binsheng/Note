package com.dev.bins.note.net

import com.dev.bins.note.bean.Authentication
import com.dev.bins.note.bean.BaseResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by bin on 21/11/2017.
 */
interface Api {


    @POST("auth/register")
    @FormUrlEncoded
    fun register(@Field("email") email: String, @Field("pwd") pwd: String): Observable<BaseResponse>

    @GET("auth/logout")
    fun logout(@Query("token") token: String): Observable<BaseResponse>

    @POST("auth/login")
    @FormUrlEncoded
    fun login(@Field("email")email:String,@Field("pwd")pwd: String):Observable<Authentication>



}