package com.app.zcoolsupport.response

import com.google.gson.annotations.SerializedName

class RaiseTicketResponse(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("message")
    val message: String?
)