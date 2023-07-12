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
import com.vms.yeshivatapp.data.model.Equipo
import com.vms.yeshivatapp.data.model.EquipoR
import com.vms.yeshivatapp.data.model.EstadisticaEquipo
import com.vms.yeshivatapp.ui.fragments.users.equipo.dialog.ysv_dialog_team_members

class ResultadosEquipoAdapter(private val dataList: List<EquipoR>, private val fragmentManager: FragmentManager) :  RecyclerView.Adapter<ResultadosEquipoAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tvTituloEquipo)
        val juegos: TextView = itemView.findViewById(R.id.tvJuegos)
        val ganados: TextView = itemView.findViewById(R.id.tvGanados)
        val perdidos: TextView = itemView.findViewById(R.id.tvPartidos)
        val goles: TextView = itemView.findViewById(R.id.tvGoles)
        val contra: TextView = itemView.findViewById(R.id.tvContra)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ysv_item_resultados, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.titleTextView.text = currentItem.equipo
        holder.juegos.text  = currentItem.num_juegos.toString()
        holder.ganados.text = currentItem.num_ganados.toString()
        holder.perdidos.text = currentItem.num_perdidos.toString()
        holder.goles.text = currentItem.num_goles.toString()
        holder.contra.text = currentItem.en_contra.toString()
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

}