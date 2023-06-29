package com.vms.yeshivatapp.ui.fragments.users.equipo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.adapter.EquiposAdapter
import com.vms.yeshivatapp.data.model.Equipo
import com.vms.yeshivatapp.data.model.EquipoModel
import com.vms.yeshivatapp.data.model.LoginResponse
import com.vms.yeshivatapp.data.model.RespuestaEquipos
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvDetalleequiposDialogfragmentBinding

import com.vms.yeshivatapp.ui.fragments.users.equipo.viewmodel.YsvDetalleEquiposViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ysv_detalleequipos_fragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var detalleEquiposViewModel: YsvDetalleEquiposViewModel
    private var _binding: YsvDetalleequiposDialogfragmentBinding? = null

    private val binding get() = _binding!!

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detalleEquiposViewModel =
            ViewModelProvider(this).get(YsvDetalleEquiposViewModel::class.java)
        _binding = YsvDetalleequiposDialogfragmentBinding.inflate(inflater, container, false)
        var root: View = binding.root
        val dataList = mutableListOf<Equipo>()
        recyclerView = binding.rVEquipos
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch{
            val call: Response<RespuestaEquipos> = RetrofitHelper.getRetrofit().create(APIService::class.java).getTeams()

                if(call.isSuccessful){
                    val user: RespuestaEquipos? = call.body()

                    if (user != null) {
                        val equipos = user.data
                        for(equipo in equipos ){
                            val EquipoM = Equipo(equipo.id_equipo, equipo.nombre, "", "", equipo.descripcion)
                            dataList.add(EquipoM)
                        }
                        requireActivity().runOnUiThread {
                            Log.e("Test_Equipo", user.data.toString())
                            val adapter = EquiposAdapter(dataList)
                            recyclerView.adapter = adapter
                        }
                    }
                }

        }



        /*val dataList = listOf(
            EquipoModel(1, "Descripción del elemento 1", R.drawable.americalogo),
            EquipoModel(2, "Descripción del elemento 2", R.drawable.americalogo),
            EquipoModel(3, "Descripción del element
            o 3", R.drawable.americalogo),
            EquipoModel(4, "Descripción del elemento 4", R.drawable.americalogo),
            EquipoModel(5, "Descripción del elemento 5", R.drawable.americalogo)
        )*/




        return root
    }

    private fun initRecyclerView() {

    }

    override fun onStart() {



        super.onStart()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}