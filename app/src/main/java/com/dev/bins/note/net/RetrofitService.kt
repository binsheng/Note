package com.dev.bins.note.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by bin on 21/11/2017.
 */
class RetrofitService private constructor(host: String) {


    companion object {
        val LEANOTE_HOST = "https://leanote.com"
        var instance: RetrofitService? = null

        fun getInstance(host: String): RetrofitService {
            if (null == instance) {
                synchronized(RetrofitService::class) {
                    if (null == instance) {
                        instance = RetrofitService(host)
                    }
                }
            }
            return instance!!
        }
    }

    val retrofit: Retrofit
    val apiServer:Api

    init {
        val builder = OkHttpClient.Builder()
        val client = builder.build()

        retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiServer = retrofit.create(Api::class.java)

    }


}