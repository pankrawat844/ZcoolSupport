package com.app.zcoolsupport

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.zcoolsupport.raiseticket.RaiseTicketActivity
import com.app.zcoolsupport.request.NewRequestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        raise_ticket.setOnClickListener {
            Intent(this@MainActivity,RaiseTicketActivity::class.java).also {
                startActivity(it)
            }
        }


        new_request.setOnClickListener {
            Intent(this@MainActivity,NewRequestActivity::class.java).also {
                startActivity(it)
            }
        }

        logout.setOnClickListener {
            val sharedPreferences=getSharedPreferences("app", Context.MODE_PRIVATE)
            sharedPreferences.edit().also {
                it.clear()
                it.commit()
            }
            Intent(this@MainActivity,LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}
