package com.example.littlefunctions

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class CameraPermission : AppCompatActivity() {
    private var cameraResultLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Approve", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Deny", Toast.LENGTH_LONG).show()
            }

        }

    private var cameraAndLocationResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                var permissionName = it.key
                var isGranted = it.value
                if (isGranted) {
                    if (permissionName == Manifest.permission.ACCESS_FINE_LOCATION) {
                        Toast.makeText(this, "Approve find location", Toast.LENGTH_LONG).show()
                    } else if (permissionName == Manifest.permission.CAMERA) {
                        Toast.makeText(this, "Approve camera", Toast.LENGTH_LONG).show()
                    } else if (permissionName == Manifest.permission.ACCESS_COARSE_LOCATION) {
                        Toast.makeText(this, "Approve coarse location", Toast.LENGTH_LONG).show()
                    }
                } else {
                    if (permissionName == Manifest.permission.ACCESS_FINE_LOCATION) {
                        Toast.makeText(this, "Deny fine location", Toast.LENGTH_LONG).show()
                    } else if (permissionName == Manifest.permission.CAMERA) {
                        Toast.makeText(this, "Deny camera", Toast.LENGTH_LONG).show()
                    } else if (permissionName == Manifest.permission.ACCESS_COARSE_LOCATION) {
                        Toast.makeText(this, "Deny coarse camera", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_permission)
        permissionForCamera()
        multiplePermission()

    }

    private fun multiplePermission() {
        var multiplePermissionBtn: Button = findViewById(R.id.multiplePermissionBtn)
        multiplePermissionBtn.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    showDialog(
                        "Need camera permission request",
                        "Cannot access to camera without permission"
                    )
                } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showDialog(
                        "Need location permission request",
                        "Cannot access to location without permission"
                    )
                } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    showDialog(
                        "Need location permission request",
                        "Cannot access to location without permission"
                    )
                } else {
                    cameraAndLocationResultLauncher.launch(
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            }
        }
    }

    private fun permissionForCamera() {
        var permissionBtn: Button = findViewById(R.id.permissionBtn)
        permissionBtn.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                    Manifest.permission.CAMERA
                )
            ) {
                showDialog(
                    "Need camera permission request",
                    "Cannot access to camera without permission"
                )
            } else {
                cameraResultLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun showDialog(title: String, message: String) {
        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title).setMessage(message)
            .setPositiveButton("Cancel") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }
}