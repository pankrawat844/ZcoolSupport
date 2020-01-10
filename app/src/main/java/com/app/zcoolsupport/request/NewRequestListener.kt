package com.app.zcoolsupport.request

interface NewRequestListener
{
    fun onStarted()
    fun onSuccess(msg:String)
    fun onFailure(msg:String)
}