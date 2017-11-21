package com.dev.bins.note.bean

import com.dev.bins.note.model.DataBase
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

/**
 * Created by bin on 21/11/2017.
 */

@Table(name = "Account",database = DataBase::class)
class Account :BaseModel(){

    var isOk = false

    var msg=""

    var localUserId:Long = -1

    var userId = ""

    var userName = ""

    var email = ""

    var verified = false

    var avatar = ""

    var accessToken = ""

    var host = ""


}