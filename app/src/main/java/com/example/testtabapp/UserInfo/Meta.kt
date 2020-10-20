package com.example.testtabapp.UserInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {
    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null
}