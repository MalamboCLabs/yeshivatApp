package com.vms.yeshivatapp.ui.fragments.users.equipo.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.databinding.YsvDialogfragmentTeammembersBinding
import com.vms.yeshivatapp.databinding.YsvTeamInfoFragmentBinding

class ysv_dialog_team_members : DialogFragment() {

     lateinit var mBinding : YsvTeamInfoFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = YsvTeamInfoFragmentBinding.inflate(inflater, container, false).apply {
        mBinding = this

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initElements()
        //initObserversAddProducts()
    }



}