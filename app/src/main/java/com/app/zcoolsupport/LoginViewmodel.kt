package com.app.zcoolsupport

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.zcoolsupport.repo.Repository
import com.app.zcoolsupport.response.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class LoginViewmodel(val repository: Repository):ViewModel() {
    var userid:String?=null
    var password:String?=null
    var loginInterface:LoginInterface?=null
    fun onSubmit(view:View)
    {

        if(userid.isNullOrEmpty() || password.isNullOrEmpty())
            loginInterface?.onFailour("Please enter userid or password.")
        else
        {
            loginInterface?.onStarted()
            CoroutineScope(Dispatchers.Main).launch {
                repository.getLogin(userid!!,password!!).enqueue(object :Callback<LoginResponse>{
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if(response.isSuccessful) {
                            response.body()?.response.let {
                                loginInterface?.OnSuccess(response?.body()!!)
                                return@let
                            }
                            loginInterface?.onFailour(response.body()?.message!!)
                        }else{
                            val jsonobj=JSONObject(response?.errorBody().toString())
                            loginInterface?.onFailour(jsonobj.getString("message"))
                        }
                    }

                })
            }
        }

    }
}
