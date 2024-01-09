package com.vms.yeshivatapp.ui.fragments.users.equipo.dialog

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.databinding.YsvInfoPlayerBinding
import com.vms.yeshivatapp.databinding.YsvTeamInfoFragmentBinding

class ysv_dialog_detail_player : DialogFragment() {

    lateinit var mBinding: YsvInfoPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = YsvInfoPlayerBinding.inflate(inflater, container, false).apply {
        mBinding = this

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvApodo.text = arguments?.getString("apodo")
        mBinding.tvNumero.text = arguments?.getInt("edad").toString()
        mBinding.tvGolesTemp.text = arguments?.getInt("golesTemp").toString()
        mBinding.tvGoleLig.text = arguments?.getInt("goleslIG").toString()
        mBinding.tvPs.text = arguments?.getString("posicion")
    }
}