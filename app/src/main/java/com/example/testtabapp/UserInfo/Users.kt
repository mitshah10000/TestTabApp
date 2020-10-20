package com.example.testtabapp.UserInfo

import com.example.testtabapp.UserInfo.Datum
import com.example.testtabapp.UserInfo.Meta
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Users {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null
}