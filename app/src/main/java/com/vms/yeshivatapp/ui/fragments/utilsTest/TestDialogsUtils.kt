package com.vms.yeshivatapp.ui.fragments.utilsTest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vms.yeshivatapp.databinding.YsvTestDialogsBinding
import com.vms.yeshivatapp.ui.utils.YsvEstatusDialogGneric

class TestDialogsUtils : Fragment() {
    private lateinit var testCoomons: TestDialogUtilsViewModel
    private var _binding: YsvTestDialogsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        testCoomons = ViewModelProvider(this).get(TestDialogUtilsViewModel::class.java)
        _binding = YsvTestDialogsBinding.inflate(inflater, container, false)
        var root: View = binding.root
        binding.dialogSucces.setOnClickListener {
            val dialogInfo = YsvEstatusDialogGneric("EQUIPO REGISTRADO", "El quipo fue registrado con éxito.", false, true, 1)
            dialogInfo.show(parentFragmentManager, "ysv")
        }
        return root
    }
}