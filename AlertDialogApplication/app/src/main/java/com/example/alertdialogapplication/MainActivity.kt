package com.example.alertdialogapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

     fun open(view:View){
         var alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
         alertDialogBuilder.setMessage("Are you sure, Your want to make decision")

         alertDialogBuilder.setPositiveButton("yes",
         DialogInterface.OnClickListener { _, _ ->
             Toast.makeText(applicationContext,"You clicked yes button",Toast.LENGTH_LONG).show();
             finish()
         })

         alertDialogBuilder.setNegativeButton("No", DialogInterface.OnClickListener { _, _ -> finish() })
         var alertDialog:AlertDialog = alertDialogBuilder.create()
         alertDialog.show()
     }
}
