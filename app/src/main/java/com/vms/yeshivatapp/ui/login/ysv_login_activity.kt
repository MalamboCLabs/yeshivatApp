package com.vms.yeshivatapp.ui.login

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
import android.provider.Settings
import android.net.Uri
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.vms.yeshivatapp.data.model.Data

class ysv_login_activity : AppCompatActivity() {
    companion object{
        lateinit var pref: SharedPreferences
    }
    private val LOCATION_REQUEST_CODE = 123
    private val REQUEST_CODE_APP_SETTINGS = 123
    private lateinit var viewModel: YsvLoginViewModel
    private lateinit var binding: YsvLoginActivityBinding
    private lateinit var db: YSVBaseDeDatos
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )
    private val PERMISSION_REQUEST_CODE = 123


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = YsvLoginActivityBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(YsvLoginViewModel::class.java)
        db = Room.databaseBuilder(applicationContext, YSVBaseDeDatos::class.java, "ysvDataBase").build()
        setContentView(binding.root)
        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        validateLoginApp()
        if (!arePermissionsGranted()) {
            showPermissionDialog("¡Necesitamos permisos para continuar!", "¿Activar?")
        }
        binding.btnLogin.setOnClickListener {
            if (!arePermissionsGranted()) {

                val shouldShowCameraRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                val shouldShowStorageRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                val shouldShowLocationRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)

                if (!shouldShowCameraRationale || !shouldShowStorageRationale || !shouldShowLocationRationale) {
                    AlertDialog.Builder(this)
                        .setMessage("Para mejorar la experiencia de la aplicación necesitamos que actives todos los permisos. :)")
                        .setPositiveButton("Configuración") { dialog, _ ->
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts("package", packageName, null)
                            intent.data = uri
                            startActivity(intent)
                        }
                        .setNegativeButton("Cancelar", { dialog, _ -> dialog.dismiss() })
                        .show()
                }

                showPermissionDialog("¡Necesitamos permisos para continuar!", "¿Activar?")
            }else{
                val alias = binding.edtUser.text.toString()
                val pass = binding.edtPassword.text.toString()

                if(alias.isEmpty() || pass.isEmpty()){
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setMessage("Necesitamos que llenes los campos para poder inicar sesion")
                        .setPositiveButton("Continuar") { dialog, _ ->
                            // Abre la pantalla de configuración de la aplicación para que el usuario active los permisos
                            dialog.dismiss()
                        }
                        .setNegativeButton("Cancelar") { dialog, _ ->
                            dialog.dismiss()
                            finish() // Puedes finalizar la actividad si el usuario cancela
                        }
                        .setCancelable(false)
                    val dialog = dialogBuilder.create()
                    dialog.show()
                }else{

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

        }
    }

    private fun validateLoginApp() {
        CoroutineScope(Dispatchers.IO).launch {
            var userDAO = db.entityDao()
            if(userDAO.obtenerTodos().isNotEmpty()){
                val listaEntidades = userDAO.obtenerTodos()
                val entidad = listaEntidades[0]
                if(!entidad.nombre_usuario.equals("")){
                    gotoDash()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun showPermissionDialog(msj: String, accion: String) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_APP_SETTINGS)
        } else {

        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_APP_SETTINGS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
            }
        }
    }



    private fun validateCredentials(alias: String, pass: String): Boolean {
        Log.e("TARG", alias)
        Log.e("TARG", pass)
        if(alias.isNotEmpty() && pass.isNotEmpty()){
            return true
        }
        return false
    }

    private fun gotoLogin(data: LoginRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<LoginResponse> = RetrofitHelper.getRetrofit().create(APIService::class.java).loginUser(data)
            withContext(Dispatchers.IO) {
                if(call.isSuccessful){
                    val user:LoginResponse? = call.body()
                    if (user != null){
                       // Log.e("DETALLES DE LA API", user.detail)
                        if(user.status.equals("SUCCESS")){
                            val data = user.data
                            when (data) {
                                is Data -> {
                                    // Tratar los datos como un objeto Data
                                    val usuario = entity(
                                        data.id_usuario, data.nombre_usuario, data.correo, data.mensaje,
                                        data.id_perfil, data.nombre_perfil, "INGRESO", "LOGIN APP"
                                    )
                                    viewModel.deleteUsers()
                                    viewModel.insertUsser(usuario)
                                    Log.e("Usuario", data.nombre_usuario)
                                    gotoDash()
                                }
                                is LinkedTreeMap<*, *> -> {
                                    val gson = Gson()
                                    val userDataJson = gson.toJson(data)
                                    val userData = gson.fromJson(userDataJson, Data::class.java)
                                    // Tratar los datos como un objeto Data
                                    val usuario = entity(
                                        userData.id_usuario, userData.nombre_usuario, userData.correo, userData.mensaje,
                                        userData.id_perfil, userData.nombre_perfil, "INGRESO", "LOGIN APP"
                                    )
                                    viewModel.deleteUsers()
                                    viewModel.insertUsser(usuario)
                                    Log.e("Usuario", userData.nombre_usuario)
                                    gotoDash()
                                }
                                else -> {
                                    // Tratar otros casos de tipos desconocidos
                                }
                            }


                        }else{
                            Log.e("ERROR", user.detail)
                            showLoginErrorDialog(user.detail)
                        }

                    }else{
                        Log.e("ERROR LOGIN", "ERROR AL CONSUMIR AL SERVICIO")
                    }

                }
            }
        }
    }

    private fun gotoDash() {
        var intent = Intent(this, ysv_main_user_fragment::class.java)
        startActivity(intent)
        finish()
    }

    fun arePermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun showLoginErrorDialog(msj: String) {
        if (!isFinishing) { // Verificar que la actividad no esté finalizando
            runOnUiThread {
                AlertDialog.Builder(this)
                    .setTitle("Error de Inicio de Sesión")
                    .setMessage("Hubo un error al iniciar sesión. Por favor, verifica tus credenciales." + msj)
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}