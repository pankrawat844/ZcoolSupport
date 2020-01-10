package com.app.zcoolsupport.raiseticket

interface RaiseTicketListener {
    fun onStarted()
    fun onCompaniesSuccess(list: List<String>)
    fun onSuccess(msg: String)
    fun onFailour(msg:String)
}