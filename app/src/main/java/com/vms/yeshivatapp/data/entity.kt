package com.vms.yeshivatapp.data

import androidx.room.*

@Entity
data class entity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "usu_name")val nombre_usuario: String,
    @ColumnInfo(name = "usu_mail")val correo: String,
    @ColumnInfo(name = "usu_msg")val mensaje: String,
    @ColumnInfo(name = "usu_perfil")val id_perfil: Int,
    @ColumnInfo(name = "usu_namep")val nombre_perfil: String,
    @ColumnInfo(name = "usu_status")val status: String,
    @ColumnInfo(name = "usu_detail")val detail: String
)

@Dao
interface entityDao {
    @Query("SELECT * FROM entity")
    fun obtenerTodos(): List<entity>

    @Query("DELETE FROM entity")
    fun deleteUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(entity: entity)
}