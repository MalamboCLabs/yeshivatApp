package com.vms.yeshivatapp.ui.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.vms.yeshivatapp.databinding.YsvDialogResultFragmentBinding


class YsvResultsDialog(juegos: String, goles:String, ganados:String, perdidos:String, contra:String ) : DialogFragment() {
    lateinit var mBinding: YsvDialogResultFragmentBinding
    private var gan:String = ganados
    private var per:String = perdidos
    private var gol:String = goles
    private var gam:String = juegos
    private var cont:String = contra
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = YsvDialogResultFragmentBinding.inflate(inflater, container, false).apply {
        mBinding = this
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvJuegos.text = gam
        mBinding.tvContra.text = cont
        mBinding.tvGanados.text = gan
        mBinding.tvPerdidos.text = per
        mBinding.tvGoles.text = gol

        mBinding.btnGeneric.setOnClickListener {
            dismiss()
        }
    }
}