package com.app.zcoolsupport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.zcoolsupport.databinding.ActivityLoginBinding
import com.app.zcoolsupport.response.LoginResponse
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),KodeinAware,LoginInterface {
    override val kodein by kodein()
    val factory:LoginViewmodelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val databinding:ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewmodel=ViewModelProviders.of(this,factory).get(LoginViewmodel::class.java)
        databinding.viewmodel=viewmodel
        viewmodel.loginInterface=this

    }

    override fun onStarted() {

    }

    override fun OnSuccess(msg: LoginResponse.Response) {
        Toast.makeText(this, msg.userid, Toast.LENGTH_SHORT).show()
    }

    override fun onFailour(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }
}
