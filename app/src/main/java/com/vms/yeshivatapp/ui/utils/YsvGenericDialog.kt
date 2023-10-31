package com.vms.yeshivatapp.ui.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.vms.yeshivatapp.R

import com.vms.yeshivatapp.databinding.YsvGenericDialogFragmentBinding

class YsvGenericDialog(txtTitle: String, txtsubTitle: String, btnContinue: Boolean, btnCancel: Boolean) : DialogFragment() {
    lateinit var mBinding: YsvGenericDialogFragmentBinding
    private var title = txtTitle
    private var subtitle = txtsubTitle
    private var continua = btnContinue
    private var cancel = btnCancel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, R.style.)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = YsvGenericDialogFragmentBinding.inflate(inflater, container, false).apply {
        mBinding = this
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.tvTitle.visibility = View.VISIBLE
        mBinding.tvTitle.text = title
        mBinding.tvMessageGeneric.visibility = View.VISIBLE
        mBinding.tvMessageGeneric.text = subtitle
        if(continua){
            mBinding.btnGeneric.visibility = View.VISIBLE
        }else{
            mBinding.btnGeneric.visibility = View.GONE
        }
        if(cancel){
            mBinding.btnCancel.visibility = View.VISIBLE
        }else{
            mBinding.btnCancel.visibility = View.GONE
        }
        mBinding.btnGeneric.setOnClickListener {
            this.dismiss()
        }

    }



}