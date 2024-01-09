package com.vms.yeshivatapp.data.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.ui.fragments.users.equipo.dialog.ysv_dialog_team_members

class PartidosAdapter(private val dataList: List<TemporadaPartidos>, private val fragmentManager: FragmentManager) : RecyclerView.Adapter<PartidosAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fecha: TextView = itemView.findViewById(R.id.tvFecha)
        val equipos: TextView = itemView.findViewById(R.id.tvEquiposP)
        val resultado: TextView = itemView.findViewById(R.id.tvResultadosP)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ysv_partido_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        for (partido in currentItem.partidos){
            holder.fecha.text  = partido.fecha
            holder.equipos.text = partido.equipos
            holder.resultado.text = partido.resultado
        }

    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}