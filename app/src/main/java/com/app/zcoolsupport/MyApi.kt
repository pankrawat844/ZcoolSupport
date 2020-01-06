package com.app.zcoolsupport

import com.app.zcoolsupport.response.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    companion object{
        operator fun invoke():MyApi
        {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://zcool.mobi/admin_panel/api/")
                .build()
                .create(MyApi::class.java)
        }
    }
@FormUrlEncoded
@POST("login.php")
    fun getLogin(@Field("userid") userid:String,
                 @Field("password") password:String):Call<LoginResponse>
}