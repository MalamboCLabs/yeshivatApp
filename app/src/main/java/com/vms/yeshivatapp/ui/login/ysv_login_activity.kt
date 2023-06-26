package com.vms.yeshivatapp.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.model.LoginRequest
import com.vms.yeshivatapp.data.model.LoginResponse
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvLoginActivityBinding
import com.vms.yeshivatapp.ui.fragments.users.ysv_main_user_fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

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

        binding.btnLogin.setOnClickListener {
            /*val intent = Intent(this, ysv_main_user_fragment::class.java)
            startActivity(intent)*/
            val alias = binding.edtUser.text.toString()
            val pass = binding.edtPassword.text.toString()
            Log.e("CREDENCIALES ALIAS", alias)
            Log.e("CREDENCIALES USUARIO", pass)
            val loginData: LoginRequest = LoginRequest(
                alias,
                pass,
                "E",
                "189.21.40.11",
                "Android",
                "Android"
            )
            gotoLogin(loginData)
        }
    }

    private fun gotoLogin(data: LoginRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<LoginResponse> = RetrofitHelper.getRetrofit().create(APIService::class.java).loginUser(data)
            runOnUiThread {
                if(call.isSuccessful){
                    val user:LoginResponse? = call.body()
                    if (user != null){
                        Log.e("DETALLES DE LA API", user.detail)
                    }
                    user?.data?.let { Log.e("Usuario", it.nombre_usuario) }
                    gotoDash()
                }
            }
        }
    }

    private fun gotoDash() {
        var intent = Intent(this, ysv_main_user_fragment::class.java)
        startActivity(intent)
    }

}