package com.vms.yeshivatapp.ui.fragments.users.partidos

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
import com.vms.yeshivatapp.data.adapter.*
import com.vms.yeshivatapp.data.model.Equipo
import com.vms.yeshivatapp.data.model.RespuestaEquipos
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvPartidosFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class ysv_partidos_fragment : Fragment()  {
    private lateinit var recyclerView: RecyclerView
    private lateinit var partidosViewModel : YsvPartidosViewModel
    private var _binding: YsvPartidosFragmentBinding? = null
    private val binding get() = _binding!!
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

        return root
    }
    override fun onStart() {



        super.onStart()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}