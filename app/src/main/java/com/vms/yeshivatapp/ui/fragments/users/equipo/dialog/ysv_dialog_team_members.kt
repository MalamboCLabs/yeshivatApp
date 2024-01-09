package com.vms.yeshivatapp.ui.fragments.users.equipo.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.adapter.EquiposAdapter
import com.vms.yeshivatapp.data.adapter.JugadorAdapter
import com.vms.yeshivatapp.data.model.*
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvDialogfragmentTeammembersBinding
import com.vms.yeshivatapp.databinding.YsvTeamInfoFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ysv_dialog_team_members : DialogFragment() {

     lateinit var mBinding : YsvTeamInfoFragmentBinding
    val dataList = mutableListOf<Jugador>()
    private lateinit var recyclerView: RecyclerView
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
        val bse64String = arguments?.getString("imageBase")
        val decodedBytes = Base64.decode(bse64String, Base64.DEFAULT)
        val decodeBitmal = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        mBinding.detailImage.setImageBitmap(decodeBitmal)
        mBinding.detailName.text = arguments?.getString("nombreEquipo")
        mBinding.detailTeam.text = arguments?.getString("descripEquip")
        getPlayersTeam()
    }

    private fun getPlayersTeam() {
        val idEquipo = arguments?.getInt("idEquipos")
        val i: Int = idEquipo?.toInt() ?: 0
        val dataPlayer: PlayerRequest = PlayerRequest(i)
        recyclerView = mBinding.rcvJugadores
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch{
            val call: Response<JsonResponse> = RetrofitHelper.getRetrofit().create(APIService::class.java).getInfoPlayers(dataPlayer)

            if(call.isSuccessful){
                val user: JsonResponse? = call.body()

                if (user != null) {
                    val equipos = user.data
                    mBinding.detailIngredients.text = equipos.size.toString()
                    for(equipo in equipos ){

                        val EquipoM = Jugador(equipo.id_jugador, equipo.id_equipo, equipo.nombre_completo, equipo.apodo, equipo.edad, equipo.escolaridad, equipo.goles_ultima_temporada, equipo.goles_total_liga, equipo.posicion, equipo.foto)
                        dataList.add(EquipoM)
                    }
                    requireActivity().runOnUiThread {
                        Log.e("Test_Equipo", user.data.toString())
                        val adapter = JugadorAdapter(dataList,childFragmentManager)
                        recyclerView.adapter = adapter
                    }
                }
            }

        }
    }

    /*Obtener los datos de los jugadores*/



}