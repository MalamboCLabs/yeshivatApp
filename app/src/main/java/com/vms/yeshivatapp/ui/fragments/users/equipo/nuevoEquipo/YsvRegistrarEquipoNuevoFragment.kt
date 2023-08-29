package com.vms.yeshivatapp.ui.fragments.users.equipo.nuevoEquipo

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.model.LoginResponse
import com.vms.yeshivatapp.data.model.RequestRegisterTeam
import com.vms.yeshivatapp.data.model.ResponseRegisterTeam
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvEquiposAltaFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class YsvRegistrarEquipoNuevoFragment : Fragment() {
    private lateinit var nuevoEquipoViewModel : YsvRegistrarEquipoNuevoViewModel
    private var _binding : YsvEquiposAltaFragmentBinding? = null
    private val binding get() = _binding!!

    private val PICK_IMAGE_REQUEST = 1
    private val CAPTURE_IMAGE_REQUEST = 2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nuevoEquipoViewModel =
            ViewModelProvider(this).get(YsvRegistrarEquipoNuevoViewModel::class.java)
        _binding =YsvEquiposAltaFragmentBinding.inflate(inflater, container, false)

        var root: View = binding.root
        val nombreEquipo = binding.edtNombreEquipo.text
        //val foto = binding.edtLogo.text
        //val logo = binding.edtFoto.text
        val descrip = binding.edtDescrip.text
        binding.button.setOnClickListener {
            showImagePickerDialog()
        }
        binding.btnRegisterTeam.setOnClickListener {
            val registerE : RequestRegisterTeam = RequestRegisterTeam(
                nombreEquipo.toString(),
                "",
                "",
                descrip.toString(),
                0
            )
            registerEquipo(registerE)
        }
        return root
    }

    fun registerEquipo(registerE: RequestRegisterTeam) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ResponseRegisterTeam> = RetrofitHelper.getRetrofit().create(APIService::class.java).RegisterTeamS(registerE)
            if(call.isSuccessful){
                    val user: ResponseRegisterTeam? = call.body()
                    if (user != null){
                        Log.e("DETALLES DE LA API", user.detail)
                    }
                if (user != null) {
                    if (user.status.equals("SUCCESS")) {
                        this.run {
                            Toast.makeText(requireContext(), "Equipo Registrado", Toast.LENGTH_SHORT).show()
                        }
                        //Toast.makeText(requireContext(), "Equipo Registrado", Toast.LENGTH_SHORT).show()
                        Log.e("REGISTRO DE EQUIPO", user.status)
                    }
                }

                }

        }
    }

    override fun onStart() {



        super.onStart()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showImagePickerDialog() {
        val items = arrayOf("Elegir de la galería", "Tomar una foto")
        AlertDialog.Builder(requireContext())
            .setTitle("Selecciona una imagen")
            .setItems(items) { _, which ->
                when (which) {
                    0 -> pickImageFromGallery()
                    1 -> captureImageFromCamera()
                }
            }
            .show()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun captureImageFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAPTURE_IMAGE_REQUEST)
    }
}