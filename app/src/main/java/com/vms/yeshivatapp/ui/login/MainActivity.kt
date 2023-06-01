package com.vms.yeshivatapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.vms.yeshivatapp.R

class MainActivity : AppCompatActivity() {
    val DURACION: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ysv_splashactivity)
        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val logo = findViewById<ImageView>(R.id.imgvLogo)
        Glide.with(this).load(R.drawable.ysvlogo).into(logo)
        gotoLogin()
    }

    private fun gotoLogin() {
        android.os.Handler().postDelayed(Runnable {
            val intent = Intent(this, ysv_login_activity::class.java)
            startActivity(intent)
        }, DURACION)
    }
}