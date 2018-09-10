package com.example.sunesh.whatsappstatusdownloader.Controller

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import com.example.sunesh.whatsappstatusdownloader.Adapter.adapterClass
import com.example.sunesh.whatsappstatusdownloader.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {



            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    100)
        }
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    101)
    }

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.


         fun onRequestPermissionsResult(requestCode: Int,
                                                permissions: Array<String>, grantResults: IntArray) {
            when (requestCode) {
                100 -> {
                    // If request is cancelled, the result arrays are empty.
                    if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.
                        Toast.makeText(this,"Granted",Toast.LENGTH_SHORT).show()
                    } else {
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(this,"Not Granted",Toast.LENGTH_SHORT).show()
                    }
                    return
                }
                101 -> {
                    // If request is cancelled, the result arrays are empty.
                    if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.
                        Toast.makeText(this,"Granted",Toast.LENGTH_SHORT).show()
                    } else {
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(this,"Not Granted",Toast.LENGTH_SHORT).show()
                    }
                    return
                }

                // Add other 'when' lines to check for other
                // permissions this app might request.
                else -> {
                    // Ignore all other requests.
                }
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){


            val path = Environment.getExternalStorageDirectory().absolutePath+"/WhatsApp/Media/.Statuses"
            try {
                val f= File(path)
                var filesarray: ArrayList<File>? = null
                val files= f.listFiles()


                val adapter = adapterClass(this,files)
                rcyrview.adapter= adapter
                val layoutManager = LinearLayoutManager(this)
                rcyrview.layoutManager = layoutManager
                rcyrview.setHasFixedSize(false)



            } catch (e: Exception){
                notfound.visibility= View.VISIBLE

            }


        }

    }
}


