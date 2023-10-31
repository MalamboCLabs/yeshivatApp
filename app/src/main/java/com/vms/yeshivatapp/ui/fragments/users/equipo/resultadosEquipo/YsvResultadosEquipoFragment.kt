package com.vms.yeshivatapp.ui.fragments.users.equipo.resultadosEquipo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.adapter.EquiposAdapter
import com.vms.yeshivatapp.data.adapter.ResultadosEquipoAdapter
import com.vms.yeshivatapp.data.model.EquipoR
import com.vms.yeshivatapp.data.model.EstadisticaEquipo
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvResultadosEquiposBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class YsvResultadosEquipoFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var resultadoEquipoViewModel : YsvResultadosEquipoViewModel
    private var _binding : YsvResultadosEquiposBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultadoEquipoViewModel =
            ViewModelProvider(this).get(YsvResultadosEquipoViewModel::class.java)
        _binding =YsvResultadosEquiposBinding.inflate(inflater, container, false)

        var root: View = binding.root

        recyclerView = binding.rvResultadosTm
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        getResults()

        return root
    }

    private fun getResults() {
        val dataListR = mutableListOf<EquipoR>()
        CoroutineScope(Dispatchers.IO).launch{
            val call: Response<EstadisticaEquipo> = RetrofitHelper.getRetrofit().create(APIService::class.java).getResultados()

            if(call.isSuccessful){
                val user: EstadisticaEquipo? = call.body()

                if (user != null) {
                    val equipos = user.data
                    for(equipo in equipos ){
                        val EquipoR = EquipoR(equipo.id_estadistica_equipo, equipo.equipo, equipo.num_juegos, equipo.num_ganados, equipo.num_perdidos, equipo.num_goles, equipo.en_contra)
                        dataListR.add(EquipoR)
                    }
                    requireActivity().runOnUiThread {
                        Log.e("Test_Equipo", user.data.toString())
                        val adapter = ResultadosEquipoAdapter(dataListR,childFragmentManager)
                        recyclerView.adapter = adapter
                    }
                }
            }

        }
    }
}