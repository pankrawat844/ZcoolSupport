package com.app.zcoolsupport

import com.app.zcoolsupport.response.LoginResponse

interface LoginInterface {
    fun onStarted()
    fun OnSuccess(msg:LoginResponse)
    fun onFailour(msg: String)

}