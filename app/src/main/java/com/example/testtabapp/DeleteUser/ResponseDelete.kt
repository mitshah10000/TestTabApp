package com.example.testtabapp.DeleteUser

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseDelete {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("meta")
    @Expose
    var meta: Any? = null

    @SerializedName("data")
    @Expose
    var data: Any? = null
}