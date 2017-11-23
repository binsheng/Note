package com.dev.bins.note.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by bin on 21/11/2017.
 */
class RetrofitService private constructor() {


    companion object {
        val LEANOTE_HOST = "https://leanote.com"
        private var INSTANCE =  RetrofitService()

        fun getInstance(): RetrofitService {
            return INSTANCE
        }

    }

    var retrofit: Retrofit? = null
    var apiServer:Api? = null

    fun init(host:String) {
        val builder = OkHttpClient.Builder()
        val client = builder.build()

        retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiServer = retrofit!!.create(Api::class.java)

    }


}