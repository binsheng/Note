package com.dev.bins.note.service

import com.dev.bins.note.net.RetrofitService

/**
 * Created by bin on 21/11/2017.
 */
class AccountService{

    fun register(email: String,password: String){
        RetrofitService.getInstance()
    }

}