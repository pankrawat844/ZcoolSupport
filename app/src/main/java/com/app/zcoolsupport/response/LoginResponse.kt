package com.app.zcoolsupport.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("response")
    val response: Response?=null,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("message")
    val message: String?
) {
    data class Response(
        @SerializedName("id")
        val id: String?,
        @SerializedName("password")
        val password: String?,
        @SerializedName("userid")
        val userid: String?
    )
}