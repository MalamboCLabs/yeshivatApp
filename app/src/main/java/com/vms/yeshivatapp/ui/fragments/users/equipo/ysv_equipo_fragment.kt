package com.vms.yeshivatapp.ui.fragments.users.equipo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.databinding.YsvEquipoFragmentBinding
import com.vms.yeshivatapp.ui.fragments.users.equipo.nuevoEquipo.YsvRegistrarEquipoNuevoFragment
import com.vms.yeshivatapp.ui.fragments.users.equipo.resultadosEquipo.YsvResultadosEquipoFragment
import com.vms.yeshivatapp.ui.fragments.users.equipo.viewmodel.YsvEquipoViewModel

class ysv_equipo_fragment : Fragment() {
    private lateinit var equipoFragmentViewModel: YsvEquipoViewModel
    private var _binding: YsvEquipoFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        equipoFragmentViewModel =
            ViewModelProvider(this).get(YsvEquipoViewModel::class.java)
        _binding = YsvEquipoFragmentBinding.inflate(inflater, container, false)

        var root: View = binding.root
        binding.btnGotoEquipos.setOnClickListener {
            val newFragment = ysv_detalleequipos_fragment() // Reemplaza "NewFragment" con el nombre de tu nuevo fragmento
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newFragment) // Reemplaza "fragmentContainer" con el ID del contenedor de fragmentos en tu actividad
                .addToBackStack(null) // Opcionalmente, agrega el fragmento actual a la pila de retroceso
                .commit()
        }
        binding.btnGotoNEquipos.setOnClickListener {
            val newFragment = YsvRegistrarEquipoNuevoFragment() // Reemplaza "NewFragment" con el nombre de tu nuevo fragmento
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newFragment) // Reemplaza "fragmentContainer" con el ID del contenedor de fragmentos en tu actividad
                .addToBackStack(null) // Opcionalmente, agrega el fragmento actual a la pila de retroceso
                .commit()

        }

        binding.btnGotoREquipos.setOnClickListener {
            val newFragment = YsvResultadosEquipoFragment() // Reemplaza "NewFragment" con el nombre de tu nuevo fragmento
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newFragment) // Reemplaza "fragmentContainer" con el ID del contenedor de fragmentos en tu actividad
                .addToBackStack(null) // Opcionalmente, agrega el fragmento actual a la pila de retroceso
                .commit()

        }
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