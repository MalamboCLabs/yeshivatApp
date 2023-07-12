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
import com.vms.yeshivatapp.data.model.EstadisticaEquipo
import com.vms.yeshivatapp.ui.fragments.users.equipo.dialog.ysv_dialog_team_members

class EquiposAdapter(private val dataList: List<Equipo>, private val fragmentManager: FragmentManager) : RecyclerView.Adapter<EquiposAdapter.MyViewHolder>(){
    private var onItemClickListener: OnItemClickListener? = null
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textView7)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textView6)
        val btnMoreInfo: Button = itemView.findViewById(R.id.btnMoreInfo)
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
            val dialogFragment = ysv_dialog_team_members()
            dialogFragment.show(fragmentManager, "YsvDialogTeamMembers")
            Log.e("ITEM EQUIPO", position.toString());
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