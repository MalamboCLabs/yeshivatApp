package com.vms.yeshivatapp.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.dao.YSVBaseDeDatos
import com.vms.yeshivatapp.data.entity
import com.vms.yeshivatapp.data.model.LoginRequest
import com.vms.yeshivatapp.data.model.LoginResponse
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvLoginActivityBinding
import com.vms.yeshivatapp.ui.fragments.users.ysv_main_user_fragment
import kotlinx.coroutines.*
import retrofit2.Response

class ysv_login_activity : AppCompatActivity() {
    companion object{
        lateinit var pref: SharedPreferences
    }
    private lateinit var viewModel: YsvLoginViewModel
    private lateinit var binding: YsvLoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = YsvLoginActivityBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(YsvLoginViewModel::class.java)
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
            withContext(Dispatchers.IO) {
                if(call.isSuccessful){
                    val user:LoginResponse? = call.body()
                    if (user != null){
                        Log.e("DETALLES DE LA API", user.detail)
                        user?.data?.let {
                            val usuario = entity(
                                it.id_usuario, it.nombre_usuario, it.correo, it.mensaje,
                                it.id_perfil, it.nombre_perfil, "INGRESO", "LOGIN APP"
                            )
                            viewModel.deleteUsers()
                            viewModel.insertUsser(usuario)
                            Log.e("Usuario", it.nombre_usuario)
                        }
                        gotoDash()
                    }

                }
            }
        }
    }

    private fun gotoDash() {
        var intent = Intent(this, ysv_main_user_fragment::class.java)
        startActivity(intent)
    }

}