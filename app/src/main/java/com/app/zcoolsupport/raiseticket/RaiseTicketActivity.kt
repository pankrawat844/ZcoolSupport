package com.app.zcoolsupport.raiseticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.zcoolsupport.R
import com.app.zcoolsupport.databinding.ActivityRaiseTicketBinding
import kotlinx.android.synthetic.main.activity_raise_ticket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RaiseTicketActivity : AppCompatActivity(),KodeinAware,RaiseTicketListener {
    override val kodein by kodein()
    lateinit var viewmodel:RaiseTicketViewModel
    val factory:RaiseTicketViewmodelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding:ActivityRaiseTicketBinding=DataBindingUtil.setContentView(this,R.layout.activity_raise_ticket)
         viewmodel=ViewModelProviders.of(this,factory).get(RaiseTicketViewModel::class.java)
        databinding.viewmodel=viewmodel
        viewmodel.raiseTicketListener=this
        viewmodel?.getCompanies()
        complaint_type.setItems("AMC","Warranty")
        priority.setItems("High","Medium","Low")
        type.setItems("Mechanical","Electrical")
        complaint_type.setOnItemSelectedListener { view, position, id, item ->
            viewmodel.complaint_type=item.toString()
//            Toast.makeText(this@RaiseTicketActivity, item.toString(), Toast.LENGTH_SHORT).show()
        }
        priority.setOnItemSelectedListener { view, position, id, item ->
            viewmodel.priority=item.toString()
//            Toast.makeText(this@RaiseTicketActivity, item.toString(), Toast.LENGTH_SHORT).show()
        }

        type.setOnItemSelectedListener { view, position, id, item ->
            viewmodel.type=item.toString()
//            Toast.makeText(this@RaiseTicketActivity, item.toString(), Toast.LENGTH_SHORT).show()
        }
    submit.setOnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            viewmodel.saveComplaint(it)
        }
    }
    }

    override fun onStarted() {
        viewmodel.name=name.text.toString()
        viewmodel.phone=phone_no.text.toString()
        viewmodel.complaint=complaint.text.toString()
    }

    override fun onCompaniesSuccess(list: List<String>) {
        company_name.setItems(list)
        company_name.setOnItemSelectedListener { view, position, id, item ->
            viewmodel.company=item.toString()
//            Toast.makeText(this@RaiseTicketActivity, item.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSuccess() {
    }

    override fun onFailour(msg: String) {

    }

}
