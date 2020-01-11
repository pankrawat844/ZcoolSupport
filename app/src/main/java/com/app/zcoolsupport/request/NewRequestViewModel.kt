package com.app.zcoolsupport.request

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.zcoolsupport.repo.Repository
import com.app.zcoolsupport.response.RaiseTicketResponse
import com.app.zcoolsupport.utils.ApiException
import com.app.zcoolsupport.utils.NoInternetException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewRequestViewModel(val repository: Repository) : ViewModel() {
    var name: String? = null
    var client: String? = null
    var phoneno: String? = null
    var email: String? = null
    var newRequestListener: NewRequestListener? = null
    fun sendRequest(view: View) {
        if (name.isNullOrEmpty()) {
            newRequestListener?.onFailure("Name not be null.")
            return
        }
        if (client.isNullOrEmpty()) {
            newRequestListener?.onFailure("Client not be null.")
            return
        }
        if (phoneno.isNullOrEmpty()) {
            newRequestListener?.onFailure("Phone Number not be null.")
            return
        }
        if (email.isNullOrEmpty()) {
            newRequestListener?.onFailure("Email not be null.")
            return
        }
        try {
            newRequestListener?.onStarted()
            CoroutineScope(Dispatchers.Main).launch {
                repository.sendRequest(name!!, client!!, phoneno!!, email!!)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            newRequestListener?.onFailure(t.message!!)
                        }

                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            if (response.isSuccessful) {
                                response.body().let {
                                    newRequestListener?.onSuccess(JSONObject(response.body()?.string()).getString(
                                        "message"
                                    ))
                                    return
                                }

                            } else {
                                newRequestListener?.onFailure(
                                    JSONObject(response.errorBody()?.string()).getString(
                                        "message"
                                    )
                                )
                            }
                        }

                    })
            }
        } catch (e: ApiException) {
            newRequestListener?.onFailure(e.message!!)


        } catch (e: NoInternetException) {
            newRequestListener?.onFailure(e.message!!)

        }

    }
}