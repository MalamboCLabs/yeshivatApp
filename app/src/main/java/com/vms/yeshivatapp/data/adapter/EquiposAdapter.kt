package com.vms.yeshivatapp.data.adapter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
        val titleTextView: TextView = itemView.findViewById(R.id.listName)
        val imgLogo: ImageView  = itemView.findViewById(R.id.imvLogo)
    //val descriptionTextView: TextView = itemView.findViewById(R.id.textView6)
        //val btnMoreInfo: Button = itemView.findViewById(R.id.btnMoreInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ysv_item_equipo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        val base64Image = currentItem.imagenLogo
        val imageDecodingTask = ImageDecodingTask(holder.imgLogo)
        imageDecodingTask.execute(base64Image)
        holder.titleTextView.text = currentItem.nombre
        //holder.descriptionTextView.text  = currentItem.descripcion
        /*GUARDAMOS LOS DATOS EN EL BUNDLE*/
        val bundle = Bundle()
        bundle.putInt("idEquipos", currentItem.id_equipo)
        bundle.putString("nombreEquipo", currentItem.nombre)
        bundle.putString("descripEquip", currentItem.descripcion)
        bundle.putString("imageBase", currentItem.imagenFoto)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
            val dialogFragment = ysv_dialog_team_members()
            dialogFragment.arguments = bundle
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

    private class ImageDecodingTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {
        override fun doInBackground(vararg params: String): Bitmap? {
            val base64Image = params[0]
            val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            if (result != null) {
                imageView.setImageBitmap(result)
            }
        }
    }
}