package com.example.streammoviesapplication.data.repository

import com.google.gson.annotations.SerializedName

data class ExampleErrorResponse(
    @SerializedName("success")
    val `success` : Boolean,
    @SerializedName("status_code")
    val `status_code`: Int,
    @SerializedName("status_message")
    val `status_message`: String

)
