package com.app.zcoolsupport.raiseticket

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.zcoolsupport.repo.Repository
import com.app.zcoolsupport.response.RaiseTicketResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RaiseTicketViewModel(val repository: Repository):ViewModel() {
    var name:String?=null
    var phone:String?=null
    var complaint:String?=null
    var company:String?=null
    var complaint_type:String?=null
    var priority:String?=null
    var userid:String?=null
    var type:String?=null
    var raiseTicketListener:RaiseTicketListener?=null
        var list:ArrayList<String>?=ArrayList()
    fun getCompanies(){
        CoroutineScope(Dispatchers.Main).launch {
            repository.getCompanies().enqueue(object :Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    raiseTicketListener?.onFailour(t.message!!)

                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                        response.body().let {
                           val jsonObject= JSONObject(it?.string())
                            val jsonArr=jsonObject.getJSONArray("companies")
                            for(i in 0 until  jsonArr.length())
                            {
                                list?.add(jsonArr.getJSONObject(i).getString("name"))
                            }
                            raiseTicketListener?.onCompaniesSuccess(list!!)
                            return@let
                        }
                    raiseTicketListener?.onFailour("Companies not Loaded.")
                }

            })
        }
    }

   suspend fun saveComplaint(view: View){
        raiseTicketListener?.onStarted()
        if(name!!.isNullOrEmpty()  || phone!!.isNullOrEmpty() || complaint!!.isNullOrEmpty() )
        {
            raiseTicketListener?.onFailour("All Fields Mandatory.")
            return

        }
//        CoroutineScope(Dispatchers.Main).launch {
            repository.saveComplaint(name!!,company!!,phone!!,complaint_type!!,priority!!,type!!,complaint!!,userid!!).enqueue(object :Callback<RaiseTicketResponse>{
                override fun onFailure(call: Call<RaiseTicketResponse>, t: Throwable) {
                    raiseTicketListener?.onFailour(t.message!!)

                }

                override fun onResponse(
                    call: Call<RaiseTicketResponse>,
                    response: Response<RaiseTicketResponse>
                ) {
                    response.body().let {

                        raiseTicketListener?.onCompaniesSuccess(list!!)
                        return@let
                    }
                    raiseTicketListener?.onFailour("Companies not Loaded.")
                }

            })
//        }
    }
}