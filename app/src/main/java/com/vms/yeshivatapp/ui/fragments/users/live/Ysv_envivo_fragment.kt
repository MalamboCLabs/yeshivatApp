package com.vms.yeshivatapp.ui.fragments.users.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vms.yeshivatapp.databinding.YsvEnvivoFragmentBinding
import com.vms.yeshivatapp.ui.fragments.users.live.viewmodel.YsvLiveViewModel

class ysv_envivo_fragment: Fragment() , OnBackPressedDispatcherOwner {
    private lateinit var yscViewModel: YsvLiveViewModel
    private var _binding: YsvEnvivoFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        yscViewModel =
            ViewModelProvider(this).get(YsvLiveViewModel::class.java)
        _binding = YsvEnvivoFragmentBinding.inflate(inflater, container, false)

        var root: View = binding.root


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // No hacer nada al presionar el botón de retroceso (bloquear funcionalidad)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return root
    }
    override fun onStart() {
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val onBackPressedDispatcher: OnBackPressedDispatcher
        get() = TODO("Not yet implemented")


}