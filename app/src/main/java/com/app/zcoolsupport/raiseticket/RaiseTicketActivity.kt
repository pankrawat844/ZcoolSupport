package com.app.zcoolsupport.raiseticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.app.zcoolsupport.R
import kotlinx.android.synthetic.main.activity_raise_ticket.*

class RaiseTicketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raise_ticket)
//        var adapter=ArrayAdapter.createFromResource(this,R.array.complaint_type,R.layout.simple_spinner_item)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        complaint_type.setItems("AMC","Warranty")

    }
}
