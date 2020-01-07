package com.app.zcoolsupport.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar

fun ProgressBar.hide()
{
    visibility= GONE

}

fun ProgressBar.show(){
    visibility=VISIBLE
}