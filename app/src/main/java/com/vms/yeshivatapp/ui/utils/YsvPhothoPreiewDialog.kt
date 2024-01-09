package com.vms.yeshivatapp.ui.utils


import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.vms.yeshivatapp.R
import com.vms.yeshivatapp.core.RetrofitHelper
import com.vms.yeshivatapp.data.model.ResponseUploadImage
import com.vms.yeshivatapp.data.model.UploadImageRequest
import com.vms.yeshivatapp.data.network.APIService
import com.vms.yeshivatapp.databinding.YsvDialogPhothopreviewFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*


class YsvPhothoPreiewDialog(bitPhto: String, typeImage: Int, nombreEquipo: String) : DialogFragment() {
    lateinit var mBinding: YsvDialogPhothopreviewFragmentBinding
    private var photoP: String = bitPhto
    private var type = typeImage
    private var buttonStateListener: ((Boolean, Int) -> Unit)? = null
    private var nEquips: String = nombreEquipo
    fun setButtonStateListener(listener: (Boolean, Int) -> Unit) {
        buttonStateListener = listener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialog)
    }

    override fun  onCreateView(inflater:  LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = YsvDialogPhothopreviewFragmentBinding.inflate(inflater, container, false).apply {
        mBinding = this

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val decodedByte = Base64.decode(photoP, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
        mBinding.imageView6.setImageBitmap(bitmap)

        mBinding.btnRetry.setOnClickListener {
            val isBottonPressed = true
            val isImage = type
            buttonStateListener?.invoke(isBottonPressed, type)
            dismiss()
        }
        mBinding.btnAceptt.setOnClickListener {
            val isBottonPressed = false
            val isImage = type
            buttonStateListener?.invoke(isBottonPressed, type)
            registerImage(photoP)
        }

    }

    @SuppressLint("LongLogTag")
    private fun registerImage(photoP: String) {
        var base64Image = photoP
        var btnNombreImgen = ""
        base64Image = base64Image.replace("\n".toRegex(), "")
        if (type.equals(1)){
            btnNombreImgen = nEquips + "_logo.jpg"
        }else{
            btnNombreImgen = nEquips + "_foto.jpg"
        }
        val registerImage : UploadImageRequest = UploadImageRequest(
            base64Image,
            btnNombreImgen
        )
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ResponseUploadImage> = RetrofitHelper.getRetrofit().create(APIService::class.java).RegisterImage(registerImage)
            if(call.isSuccessful){
                val image: ResponseUploadImage? = call.body()
                if(image != null){
                    Log.e("DETALLES DE LA CARGA DE IMAGEN", image.status)
                    if(image.status.equals("SUCCESS")){
                        val isBottonPressed = false
                        buttonStateListener?.invoke(isBottonPressed, type)
                        dialog?.dismiss()
                    }
                }
            }
        }
    }

}