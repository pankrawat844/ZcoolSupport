package com.app.zcoolsupport.repo

import com.app.zcoolsupport.MyApi
import com.app.zcoolsupport.response.LoginResponse
import com.app.zcoolsupport.response.RaiseTicketResponse
import okhttp3.ResponseBody
import retrofit2.Call

class Repository(val myApi: MyApi) {

    suspend fun getLogin(userid:String,password:String):Call<LoginResponse>{
        return myApi.getLogin(userid, password)
    }

    suspend fun getCompanies():Call<ResponseBody>
    {
        return myApi.companiesList()
    }

    suspend fun saveComplaint(name:String,company:String,phoneno:String,complaint_type:String,priority:String,type:String,complaint:String,userid: String):Call<RaiseTicketResponse>
    {
        return myApi.saveComplaint(name,company,phoneno,complaint_type, priority,type, complaint, userid)
    }

    suspend fun sendRequest(name: String,client:String,phoneno: String,email:String):Call<ResponseBody>{
        return myApi.sendRequest(name,client,phoneno,email)
    }
}