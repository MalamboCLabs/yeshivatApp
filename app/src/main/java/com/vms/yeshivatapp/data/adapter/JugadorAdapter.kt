package com.vms.yeshivatapp.data.adapter

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.data.model.Jugador
import com.vms.yeshivatapp.ui.fragments.users.equipo.dialog.ysv_dialog_detail_player

class JugadorAdapter(private val dataList: List<Jugador>, private val fragmentManger: FragmentManager): RecyclerView.Adapter<JugadorAdapter.MyViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleJugadorName: TextView = itemView.findViewById(R.id.playerName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ysv_item_name_player, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JugadorAdapter.MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        val bundle = Bundle()
        bundle.putString("apodo", currentItem.apodo)
        bundle.putInt("edad", currentItem.edad)
        currentItem.goles_ultima_temporada?.let { bundle.putInt("golesTemp", it) }
        currentItem.goles_total_liga?.let { bundle.putInt("golesLig", it) }
        bundle.putString("posicion", currentItem.posicion)
        holder.titleJugadorName.text = currentItem.nombre_completo
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
            val dialogTeam = ysv_dialog_detail_player()
            dialogTeam.arguments = bundle
            dialogTeam.show(fragmentManger, "infoPlayter")
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}