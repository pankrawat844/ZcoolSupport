package com.app.zcoolsupport.request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.zcoolsupport.R
import com.app.zcoolsupport.databinding.ActivityNewRequestBinding
import com.app.zcoolsupport.utils.hide
import com.app.zcoolsupport.utils.show
import kotlinx.android.synthetic.main.activity_new_request.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class NewRequestActivity : AppCompatActivity(),KodeinAware,NewRequestListener {
    override val kodein by kodein()
    val factory:NewRequestViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding:ActivityNewRequestBinding=DataBindingUtil.setContentView(this,R.layout.activity_new_request)
        val viewModel=ViewModelProviders.of(this,factory).get(NewRequestViewModel::class.java)
        databinding.viewmodel=viewModel
        viewModel.newRequestListener=this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(msg: String) {
        Toast.makeText(this@NewRequestActivity, msg, Toast.LENGTH_SHORT).show()
        progress_bar.hide()
        finish()
    }

    override fun onFailure(msg: String) {
        Toast.makeText(this@NewRequestActivity, msg, Toast.LENGTH_SHORT).show()
        progress_bar.hide()
    }
}
