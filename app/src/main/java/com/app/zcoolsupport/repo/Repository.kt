package com.app.zcoolsupport.repo

import com.app.zcoolsupport.MyApi
import com.app.zcoolsupport.response.LoginResponse
import retrofit2.Call

class Repository(val myApi: MyApi) {

    suspend fun getLogin(userid:String,password:String):Call<LoginResponse>{
        return myApi.getLogin(userid, password)
    }
}