package com.vms.yeshivatapp.ui.fragments.users.equipo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.data.adapter.EquiposAdapter
import com.vms.yeshivatapp.data.model.EquipoModel
import com.vms.yeshivatapp.databinding.YsvDetalleequiposDialogfragmentBinding

import com.vms.yeshivatapp.ui.fragments.users.equipo.viewmodel.YsvDetalleEquiposViewModel

class ysv_detalleequipos_fragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var detalleEquiposViewModel: YsvDetalleEquiposViewModel
    private var _binding: YsvDetalleequiposDialogfragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detalleEquiposViewModel =
            ViewModelProvider(this).get(YsvDetalleEquiposViewModel::class.java)
        _binding = YsvDetalleequiposDialogfragmentBinding.inflate(inflater, container, false)
        var root: View = binding.root

        recyclerView = binding.rVEquipos
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val dataList = listOf(
            EquipoModel(1, "Descripción del elemento 1", R.drawable.americalogo),
            EquipoModel(2, "Descripción del elemento 2", R.drawable.americalogo),
            EquipoModel(3, "Descripción del elemento 3", R.drawable.americalogo),
            EquipoModel(4, "Descripción del elemento 4", R.drawable.americalogo),
            EquipoModel(5, "Descripción del elemento 5", R.drawable.americalogo)
        )

        val adapter = EquiposAdapter(dataList)
        recyclerView.adapter = adapter


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