package com.vms.yeshivatapp.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.vms.yeshivatapp.data.dao.YSVBaseDeDatos
import com.vms.yeshivatapp.data.entity

class YsvLoginViewModel(application: Application): AndroidViewModel(application) {
    private val db = Room.databaseBuilder(application, YSVBaseDeDatos::class.java, "ysvDataBase").build()
    private val userDao = db.entityDao()

    suspend fun insertUsser(usuario: entity){
        userDao.insertar(usuario)
    }
    suspend fun obtenerUsuario(): List<entity>{
        return userDao.obtenerTodos()
    }

    suspend fun deleteUsers(){
        userDao.deleteUsers()
    }
}