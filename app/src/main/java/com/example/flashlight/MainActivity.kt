package com.example.flashlight

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import com.example.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var cameraManager: CameraManager
    var isFlash = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        binding.toggleBtn.setOnClickListener {
            flashLightOnAndOf(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashLightOnAndOf(v: View?) {
        if(!isFlash){
            val cameraListId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraListId, true)
            isFlash = true
            binding.toggleBtn.setImageResource(R.drawable.btn_onn)
            Toast.makeText(this, "Flash Light is on", Toast.LENGTH_SHORT).show()
        }else{
            val cameraListId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraListId, false)
            isFlash = false
            binding.toggleBtn.setImageResource(R.drawable.btn_off)
            Toast.makeText(this, "Flash Light is off", Toast.LENGTH_SHORT).show()
        }
    }
}