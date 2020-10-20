package com.example.testtabapp.UserInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pagination {
    @SerializedName("total")
    @Expose
    var total: Int? = null

    @SerializedName("pages")
    @Expose
    var pages: Int? = null

    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("limit")
    @Expose
    var limit: Int? = null
}