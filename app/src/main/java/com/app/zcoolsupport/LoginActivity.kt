package com.app.zcoolsupport

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.zcoolsupport.databinding.ActivityLoginBinding
import com.app.zcoolsupport.response.LoginResponse
import com.app.zcoolsupport.utils.hide
import com.app.zcoolsupport.utils.show
import kotlinx.android.synthetic.main.activity_login.*
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
        progress_bar.show()
    }

    override fun OnSuccess(msg: LoginResponse.Response) {
        progress_bar.hide()
        Toast.makeText(this, "Login Successfully.", Toast.LENGTH_SHORT).show()
        val sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        sharedPreferences.edit().also {
            it.putBoolean("islogin", true)
            it.putString("userid", msg.userid)
            it.putString("id", msg.id)
            it.apply()
        }
        Intent(this@LoginActivity, MainActivity::class.java).also {
            finish()
            startActivity(it)
        }
    }

    override fun onFailour(msg: String) {
        progress_bar.hide()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }
}
