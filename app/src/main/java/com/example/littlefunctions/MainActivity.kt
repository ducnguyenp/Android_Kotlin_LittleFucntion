package com.example.littlefunctions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var cameraPermissionBtn: Button? = null
    private var toastBtn: Button? = null
    private var coroutinesBtn: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraPermissionBtn = findViewById(R.id.cameraPermissionBtn)
        cameraPermissionBtn?.setOnClickListener() {
            val intent = Intent(this, CameraPermission::class.java)
            startActivity(intent)
        }
        toastBtn = findViewById(R.id.toastBtn)
        toastBtn?.setOnClickListener() {
            val intent = Intent(this, MultipleDialog::class.java)
            startActivity(intent)
        }
        coroutinesBtn = findViewById(R.id.coroutinesBtn)
        coroutinesBtn?.setOnClickListener() {
            val intent = Intent(this, Coroutines::class.java)
            startActivity(intent)
        }
    }
}