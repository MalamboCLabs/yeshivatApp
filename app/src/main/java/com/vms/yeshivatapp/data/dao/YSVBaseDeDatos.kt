package com.vms.yeshivatapp.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vms.yeshivatapp.data.entity
import com.vms.yeshivatapp.data.entityDao
@Database(entities = [entity::class], version = 1, exportSchema = false)
abstract class YSVBaseDeDatos : RoomDatabase() {
    abstract fun entityDao() : entityDao

    companion object {
        private var INSTANCE: YSVBaseDeDatos? = null

        fun getInstance(context: Context): YSVBaseDeDatos {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    YSVBaseDeDatos::class.java,
                    "ysvDataBase"
                ).build()
            }
            return INSTANCE as YSVBaseDeDatos
        }
    }
}