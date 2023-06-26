package com.vms.yeshivatapp.data.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.data.model.EquipoModel

class EquiposAdapter(private val dataList: List<EquipoModel>) : RecyclerView.Adapter<EquiposAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        //val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ysv_item_equipo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        //holder.titleTextView.text = currentItem.title
        //holder.descriptionTextView.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}