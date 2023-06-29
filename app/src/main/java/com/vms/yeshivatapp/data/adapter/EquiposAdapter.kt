package com.vms.yeshivatapp.data.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.data.model.Equipo
import com.vms.yeshivatapp.data.model.EquipoModel

class EquiposAdapter(private val dataList: List<Equipo>) : RecyclerView.Adapter<EquiposAdapter.MyViewHolder>(){
    private var onItemClickListener: OnItemClickListener? = null
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textView7)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textView6)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ysv_item_equipo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.titleTextView.text = currentItem.nombre
        holder.descriptionTextView.text  = currentItem.descripcion
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    //holder.titleTextView.text = currentItem.title
        //holder.descriptionTextView.text = currentItem.description
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