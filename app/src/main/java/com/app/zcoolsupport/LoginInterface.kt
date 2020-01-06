package com.app.zcoolsupport

import com.app.zcoolsupport.response.LoginResponse

interface LoginInterface {
    fun onStarted()
    fun OnSuccess(msg:LoginResponse.Response)
    fun onFailour(msg: String)

}