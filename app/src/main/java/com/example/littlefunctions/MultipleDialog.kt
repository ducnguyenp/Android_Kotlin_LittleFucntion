package com.example.littlefunctions

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MultipleDialog : AppCompatActivity() {
    var snackToastBtn: Button? = null
    var dialogBtn: Button? = null
    var customDialogBtn: Button? = null
    var customProgressDialogBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        showSnakeToast()
        showDialog()
        showCustomDialog()
        showCustomProgressDialog()
    }

    private fun showSnakeToast() {
        snackToastBtn = findViewById(R.id.snackToastBtn)
        snackToastBtn?.setOnClickListener() {
            Snackbar.make(it, "You have clicked on snack bar", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showCustomDialog() {
        customDialogBtn = findViewById(R.id.customDialogBtn)
        customDialogBtn?.setOnClickListener() {
            customDialogFun()
        }
    }

    private fun showCustomProgressDialog() {
        customProgressDialogBtn = findViewById(R.id.customProgressDialogBtn)
        customProgressDialogBtn?.setOnClickListener() {
            customProgressDialogFun()
        }
    }

    private fun customProgressDialogFun() {
        var customProgressDialog = Dialog(this)
        customProgressDialog.setContentView(R.layout.custom_progress_dialog)
        customProgressDialog.show()
    }

    private fun customDialogFun() {
        var customDialog = Dialog(this)
        customDialog.setContentView(R.layout.custom_dialog)
        customDialog.findViewById<Button>(R.id.yesBtn).setOnClickListener() {
            Toast.makeText(applicationContext, "Yes", Toast.LENGTH_LONG).show()
            customDialog.dismiss()
        }
        customDialog.findViewById<Button>(R.id.noBtn).setOnClickListener() {
            Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun showDialog() {
        dialogBtn = findViewById(R.id.dialogBtn)
        dialogBtn?.setOnClickListener() {
            alertDialogFun()
        }
    }

    private fun alertDialogFun() {
        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("This is an alert")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes") { dialog, _ ->
            Toast.makeText(this, "Yes", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
        builder.setNeutralButton("Cancel") { dialog, _ ->
            Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
        var alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true) //Todo: allow user to click on background to close dialog
        alertDialog.show()
    }
}