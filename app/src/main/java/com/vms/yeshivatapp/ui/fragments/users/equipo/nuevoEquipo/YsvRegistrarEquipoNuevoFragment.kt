package com.vms.yeshivatapp.ui.fragments.users.equipo.nuevoEquipo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.model.LoginResponse
import com.vms.yeshivatapp.data.model.RequestRegisterTeam
import com.vms.yeshivatapp.data.model.ResponseRegisterTeam
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvEquiposAltaFragmentBinding
import com.vms.yeshivatapp.ui.utils.YsvEstatusDialogGneric
import com.vms.yeshivatapp.ui.utils.YsvGenericDialog
import com.vms.yeshivatapp.ui.utils.YsvPhothoPreiewDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream

class YsvRegistrarEquipoNuevoFragment : Fragment() {
    private lateinit var nuevoEquipoViewModel : YsvRegistrarEquipoNuevoViewModel
    private var _binding : YsvEquiposAltaFragmentBinding? = null
    private val binding get() = _binding!!

    private val PICK_IMAGE_REQUEST = 1
    private val CAPTURE_IMAGE_REQUEST = 2
    private var FLAG_TIPE_IMAGE = 0;

    private var imgLogoBs: String = ""
    private var imgEquipo: String = ""
    private var buttonEnable = true
    var nomEquip: String =""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nuevoEquipoViewModel =
            ViewModelProvider(this).get(YsvRegistrarEquipoNuevoViewModel::class.java)
        _binding =YsvEquiposAltaFragmentBinding.inflate(inflater, container, false)

        var root: View = binding.root
        val  nombreEquipo = binding.edtNombreEquipo.text
        binding.button.isEnabled = false
        binding.button2.isEnabled = false
        binding.edtNombreEquipo.doAfterTextChanged {
            if(nombreEquipo.toString() != ""){
                binding.button.isEnabled = true
                binding.button2.isEnabled = true
            }
        }


        //val foto = binding.edtLogo.text
        //val logo = binding.edtFoto.text
        val descrip = binding.edtDescrip.text

        binding.button.setOnClickListener {
            FLAG_TIPE_IMAGE = 1
            nomEquip = nombreEquipo.toString()
            showImagePickerDialog(1)
        }
        binding.button2.setOnClickListener {

            nomEquip = nombreEquipo.toString()
            FLAG_TIPE_IMAGE = 2
            showImagePickerDialog(2)
        }
        binding.btnRegisterTeam.setOnClickListener {
            if(validateInputs()){
                imgLogoBs = "/assets/img/"+ nombreEquipo + "_logo.jpg"
                imgEquipo = "/assets/img/"+ nombreEquipo + "_foto.jpg"
                val registerE : RequestRegisterTeam = RequestRegisterTeam(
                    nombreEquipo.toString(),
                    imgLogoBs,
                    imgEquipo,
                    descrip.toString(),
                    0
                )
                registerEquipo(registerE)
            }else{
                val genericDialog = YsvGenericDialog("FALTAN DATOS", "Por favor llena todos los datos Nombre del equipo, foto, logo, descripcion del equipo.", true, false)
                genericDialog.show(parentFragmentManager, "ysv")
            }
        }
        return root
    }

    private fun validateInputs(): Boolean {
        if(binding.edtDescrip.text.isNotEmpty() && binding.edtNombreEquipo.text.isNotEmpty() && imgEquipo.isNotEmpty() && imgLogoBs.isNotEmpty()){
            return true;
        }
        return false
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
                            val dialogInfo = YsvEstatusDialogGneric("EQUIPO REGISTRADO", "El quipo fue registrado con éxito.", false, true, 1)
                            dialogInfo.show(parentFragmentManager, "ysv")
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


    private fun showImagePickerDialog(reqC: Int) {
        val items = arrayOf("Elegir de la galería", "Tomar una foto")
        requireActivity().runOnUiThread{
            AlertDialog.Builder(requireContext())
                .setTitle("Selecciona una imagen")
                .setItems(items) { _, which ->
                    when (which) {
                        0 -> pickImageFromGallery(reqC)
                        1 -> captureImageFromCamera(reqC)
                    }
                }
                .show()
        }
    }

    private fun pickImageFromGallery(req: Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun captureImageFromCamera(req: Int) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAPTURE_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                PICK_IMAGE_REQUEST -> {
                    val selectImageUri = data?.data
                    val imageBitmap = selectImageUri?.let{
                        requireContext().contentResolver.openInputStream(it)?.use {
                            BitmapFactory.decodeStream(it)
                        }
                    }

                    if(imageBitmap != null){
                        if(FLAG_TIPE_IMAGE == 1){

                            imgEquipo = convertBitmapToBase64(imageBitmap)
                            val alertPreview = YsvPhothoPreiewDialog(imgEquipo,FLAG_TIPE_IMAGE, nomEquip);
                            alertPreview.setButtonStateListener { isButtonPressed, isImage ->
                                run {
                                    if (isButtonPressed) {
                                        showImagePickerDialog(isImage)
                                    }else{
                                        buttonEnable = false
                                    }
                                }
                            }
                            alertPreview.show(parentFragmentManager, "ysvPreviewPhotho")
                        }
                        else{
                            imgLogoBs = convertBitmapToBase64(imageBitmap)
                            val alertPreview = YsvPhothoPreiewDialog(imgLogoBs,FLAG_TIPE_IMAGE, nomEquip);
                            alertPreview.show(parentFragmentManager, "ysvPreviewPhotho")
                            alertPreview.setButtonStateListener { isButtonPressed, isImage ->
                                run {
                                    if (isButtonPressed) {
                                        showImagePickerDialog(isImage)
                                    }else{
                                        buttonEnable = false
                                    }
                                }
                            }
                        }

                    }
                    //imgLogoBs = convertBitmapToBase64(selectImageUri)
                }
                CAPTURE_IMAGE_REQUEST ->{
                    val imageBitmap = data?.extras?.get("data") as? Bitmap
                    if(FLAG_TIPE_IMAGE == 1){
                        imgEquipo = imageBitmap?.let { convertBitmapToBase64(it) }.toString()
                        val alertPreview = YsvPhothoPreiewDialog(imgEquipo,FLAG_TIPE_IMAGE, nomEquip);
                        alertPreview.setButtonStateListener { isButtonPressed, isImage ->
                            run {
                                if (isButtonPressed) {
                                    showImagePickerDialog(isImage)
                                }else{
                                    buttonEnable = false
                                }
                            }
                        }
                        alertPreview.show(parentFragmentManager, "ysvPreviewPhotho")

                    }else{
                        imgLogoBs = imageBitmap?.let { convertBitmapToBase64(it) }.toString()
                        val alertPreview = YsvPhothoPreiewDialog(imgLogoBs,FLAG_TIPE_IMAGE, nomEquip);
                        alertPreview.setButtonStateListener { isButtonPressed, isImage ->
                            run {
                                if (isButtonPressed) {
                                    showImagePickerDialog(isImage)
                                }else{
                                    buttonEnable = false
                                }
                            }
                        }
                        alertPreview.show(parentFragmentManager, "ysvPreviewPhotho")
                    }

                }
            }
        }
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


}