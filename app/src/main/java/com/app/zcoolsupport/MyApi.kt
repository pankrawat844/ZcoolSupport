package com.app.zcoolsupport

import com.app.zcoolsupport.response.LoginResponse
import com.app.zcoolsupport.response.RaiseTicketResponse
import com.app.zcoolsupport.utils.NetworkConnectionInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface MyApi {

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ):MyApi
        {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()
            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://zcool.mobi/admin_panel/api/")
                .build()
                .create(MyApi::class.java)
        }
    }
@FormUrlEncoded
@POST("login.php")
    fun getLogin(@Field("userid") userid:String,
                 @Field("password") password:String):Call<LoginResponse>

    @GET("companies_list.php")
    fun companiesList():Call<ResponseBody>
    @FormUrlEncoded
    @POST("raise_ticket1.php")
    fun saveComplaint(
        @Field("name")name:String,
        @Field("company") company:String,
        @Field("phoneno") phone:String,
        @Field("complaint_type") complaint_type:String,
        @Field("priority") priority:String,
        @Field("type") type:String,
        @Field("complaint") complaint:String,
        @Field("userid") userid:String

    ):Call<RaiseTicketResponse>

    @FormUrlEncoded
    @POST("send_mail_admin.php")
    fun sendRequest(
        @Field("name")name:String,
        @Field("client") client:String,
        @Field("contact") phone:String,
        @Field("mail") email:String
    ):Call<ResponseBody>
}