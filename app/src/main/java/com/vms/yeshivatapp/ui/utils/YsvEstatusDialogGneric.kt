package com.vms.yeshivatapp.ui.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.databinding.YsvDialogStatusInfoBinding


class YsvEstatusDialogGneric(title: String, subTitle:String, retry:Boolean, seg:Boolean, typeDialog:Int) : DialogFragment() {
    lateinit var mBinding: YsvDialogStatusInfoBinding
    private var title:String = title
    private var subtitle:String = subTitle
    private var retri:Boolean = retry
    private var seg:Boolean = seg
    private var typeDialog:Int = typeDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialog)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = YsvDialogStatusInfoBinding.inflate(inflater, container, false).apply {

        mBinding = this


    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(typeDialog == 1){
            mBinding.imageView4.setImageResource(R.drawable.ysv_ic_success)
            mBinding.tvTitle.text = title
            mBinding.tvErrorTitle.text = title

            mBinding.tvErrorDescription.text = subtitle
            mBinding.btnRetry.visibility = View.VISIBLE
            mBinding.btnRetry.text ="CONTINUAR"
            mBinding.tvErrorSubtitle.text = ""
            mBinding.ivBackground.setBackgroundResource(R.drawable.ysv_wave_success)
            mBinding.btnRetry.setOnClickListener {
                dismiss()
            }
        }
        else if(typeDialog == 0){
            mBinding.imageView4.setImageResource(R.drawable.ysv_ic_error)
            mBinding.tvTitle.text = title
            mBinding.tvErrorTitle.text = title
            mBinding.tvErrorDescription.text = subtitle
            mBinding.btnRetry.visibility = View.VISIBLE
            mBinding.btnRetry.text ="CONTINUAR"
            mBinding.tvErrorSubtitle.text = ""
            mBinding.ivBackground.setBackgroundResource(R.drawable.ysv_wave_danger)
            mBinding.btnRetry.setOnClickListener {
                dismiss()
            }
        }
    }

}