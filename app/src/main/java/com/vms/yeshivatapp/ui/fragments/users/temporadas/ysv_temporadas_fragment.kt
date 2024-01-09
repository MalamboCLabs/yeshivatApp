package com.vms.yeshivatapp.ui.fragments.users.temporadas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vms.yeshivatapp.databinding.YsvTemporadasFragmentBinding

class ysv_temporadas_fragment: Fragment() {
    private lateinit var temporadasViewmodel: ysv_temporadas_viewModel
    private var _binding: YsvTemporadasFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        temporadasViewmodel = ViewModelProvider(this).get(ysv_temporadas_viewModel::class.java)
        _binding = YsvTemporadasFragmentBinding.inflate(inflater, container, false)
        var root: View = binding.root
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