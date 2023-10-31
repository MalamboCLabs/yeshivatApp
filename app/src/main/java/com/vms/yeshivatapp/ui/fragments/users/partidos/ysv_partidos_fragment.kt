package com.vms.yeshivatapp.ui.fragments.users.partidos

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.adapter.*
import com.vms.yeshivatapp.data.model.Equipo
import com.vms.yeshivatapp.data.model.RespuestaEquipos
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvPartidosFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class ysv_partidos_fragment : Fragment()  {
    private lateinit var recyclerView: RecyclerView
    private lateinit var partidosViewModel : YsvPartidosViewModel
    private var _binding: YsvPartidosFragmentBinding? = null
    private val binding get() = _binding!!
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        partidosViewModel =
            ViewModelProvider(this).get(YsvPartidosViewModel::class.java)
        _binding = YsvPartidosFragmentBinding.inflate(inflater, container, false)
        var root: View = binding.root
        val dataList = mutableListOf<TemporadaPartidos>()
        recyclerView = binding.rcvPartidos
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        CoroutineScope(Dispatchers.IO).launch{
            val call: Response<PartidoDetalleResponse> = RetrofitHelper.getRetrofit().create(APIService::class.java).getGames()

            if(call.isSuccessful){
                val user: PartidoDetalleResponse? = call.body()

                if (user != null) {
                    val equipos = user.data
                    for(equipo in equipos ){
                        val EquipoM =  TemporadaPartidos(equipo.temporada, equipo.partidos)
                        dataList.add(EquipoM)
                    }
                    requireActivity().runOnUiThread {
                        Log.e("Test_Equipo", user.data.toString())
                        val adapter = PartidosAdapter(dataList,childFragmentManager)
                        recyclerView.adapter = adapter
                    }
                }
            }

        }
        binding.button4.setOnClickListener {
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("Select dates")
                    .build()

            dateRangePicker.show(childFragmentManager, "pkr")

            dateRangePicker.addOnPositiveButtonClickListener {
                convertirMarcaDeTiempoAMostrar(it.first)
                Log.e("DATE PIKER",  convertirMarcaDeTiempoAMostrar(it.first) )
            }
            dateRangePicker.addOnNegativeButtonClickListener {
                // Respond to negative button click.
            }
            dateRangePicker.addOnCancelListener {
                // Respond to cancel button click.
            }
            dateRangePicker.addOnDismissListener {
                // Respond to dismiss events.
            }
        }
        return root
    }
    override fun onStart() {



        super.onStart()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun convertirMarcaDeTiempoAMostrar(marcaDeTiempo: Long): String {
        val instant = Instant.ofEpochMilli(marcaDeTiempo)
        val zoneId = ZoneId.systemDefault() // Puedes cambiar esto a la zona horaria que desees
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return instant.atZone(zoneId).format(formatter)
    }

}