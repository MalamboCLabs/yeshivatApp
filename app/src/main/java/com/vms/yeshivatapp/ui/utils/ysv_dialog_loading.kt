package com.vms.yeshivatapp.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.databinding.YsvDialogProgressBinding
import com.vms.yeshivatapp.databinding.YsvTeamInfoFragmentBinding

class ysv_dialog_loading : DialogFragment() {
    lateinit var mBinding: YsvDialogProgressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = YsvDialogProgressBinding.inflate(inflater, container, false).apply {
        mBinding = this

    }.root
}