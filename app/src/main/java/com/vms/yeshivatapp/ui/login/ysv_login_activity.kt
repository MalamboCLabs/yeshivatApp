package com.vms.yeshivatapp.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.databinding.YsvLoginActivityBinding

class ysv_login_activity : AppCompatActivity() {
    companion object{
        lateinit var pref: SharedPreferences
    }
    private lateinit var binding: YsvLoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = YsvLoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //pref = SharedPreferences(baseContext)
        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

}