package com.vms.yeshivatapp.ui.fragments.users

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.data.dao.YSVBaseDeDatos
import com.vms.yeshivatapp.databinding.YsvLoginActivityBinding
import com.vms.yeshivatapp.databinding.YsvMenuHeaderBinding
import com.vms.yeshivatapp.ui.fragments.users.equipo.ysv_equipo_fragment
import com.vms.yeshivatapp.ui.fragments.users.live.ysv_envivo_fragment
import com.vms.yeshivatapp.ui.fragments.users.partidos.ysv_partidos_fragment
import com.vms.yeshivatapp.ui.fragments.utilsTest.TestDialogsUtils
import kotlinx.coroutines.*

class ysv_main_user_fragment: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var db: YSVBaseDeDatos

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(applicationContext, YSVBaseDeDatos::class.java, "ysvDataBase").build()
        setContentView(R.layout.ysv_activity_main_user)
        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_item)
        navigationView.setNavigationItemSelectedListener(this)
        val headerView: View = navigationView.getHeaderView(0)
        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation, R.string.close_navigation)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ysv_envivo_fragment()).commit()
            navigationView.setCheckedItem(R.id.nav_item)
        }

        CoroutineScope(Dispatchers.IO).launch {
            var userDAO = db.entityDao()
            if(userDAO.obtenerTodos().isNotEmpty()){
                val listaEntidades = userDAO.obtenerTodos()
                val entidad = listaEntidades[0]
                val tUser :TextView = headerView.findViewById(R.id.tvUser)
                val tPuesto :TextView = headerView.findViewById(R.id.tvPuesto)
                Log.e("Nombre de usuario", entidad.nombre_usuario)
                tUser.text = "${entidad.nombre_usuario}"
                tPuesto.text = "${entidad.nombre_perfil}"
                //binding.tvPuesto.text = "${entidad.nombre_perfil}"
            }
        }
        //getDataUser()

    }

    private fun getDataUser() {

        CoroutineScope(Dispatchers.IO).launch {
            var userDAO = db.entityDao()
            if(userDAO.obtenerTodos().isNotEmpty()){
                val listaEntidades = userDAO.obtenerTodos()
                val entidad = listaEntidades[0]
                val tUser :TextView = findViewById<TextView>(R.id.tvUser)
                Log.e("Nombre de usuario", entidad.nombre_usuario)
                tUser.text = "${entidad.nombre_usuario}"
                //binding.tvPuesto.text = "${entidad.nombre_perfil}"
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ysvEnvivo -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ysv_envivo_fragment()).commit()
            R.id.ysvEquipo -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ysv_equipo_fragment()).commit()
            R.id.ysvPartidos -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ysv_partidos_fragment()).commit()
            R.id.ysvTestUtils  -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TestDialogsUtils()).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}