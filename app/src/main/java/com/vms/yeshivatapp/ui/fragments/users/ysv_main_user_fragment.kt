package com.vms.yeshivatapp.ui.fragments.users

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.ui.fragments.users.equipo.ysv_equipo_fragment
import com.vms.yeshivatapp.ui.fragments.users.live.ysv_envivo_fragment
import com.vms.yeshivatapp.ui.fragments.users.partidos.ysv_partidos_fragment

class ysv_main_user_fragment: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ysv_activity_main_user)
        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_item)
        navigationView.setNavigationItemSelectedListener(this)

        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation, R.string.close_navigation)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ysv_envivo_fragment()).commit()
            navigationView.setCheckedItem(R.id.nav_item)
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
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}