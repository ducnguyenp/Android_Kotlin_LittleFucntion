package com.example.littlefunctions

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Todo: so when the app takes to long to execute a function and
// android automatically understand that this is not good so it will show a pop up
// to notify user to close app to avoid bad experience
class Coroutines : AppCompatActivity() {
    var customProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        var executeBtn: Button = findViewById(R.id.executeBtn)
        executeBtn.setOnClickListener() {
            showProcessDialog()
            lifecycleScope.launch {
                onExecute("Task done")
            }
        }

    }

    private suspend fun onExecute(result: String) {
        withContext(Dispatchers.IO) {
            for (i in 1..100000) {
                Log.e("Delay: ", "" + i)
            }
            runOnUiThread() {
                // Todo when you want to run anything, put it in here
                cancelProcessDialog()
                Toast.makeText(this@Coroutines, result, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showProcessDialog() {
        customProgressDialog = Dialog(this)
        customProgressDialog?.setContentView(R.layout.custom_progress_dialog)
        customProgressDialog?.show()
    }

    private fun cancelProcessDialog() {
        if (customProgressDialog != null) {
            customProgressDialog?.dismiss()
            customProgressDialog = null
        }
    }
}