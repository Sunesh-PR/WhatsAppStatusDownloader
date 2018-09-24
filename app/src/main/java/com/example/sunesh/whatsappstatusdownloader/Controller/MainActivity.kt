package com.example.sunesh.whatsappstatusdownloader.Controller

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
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
import com.example.sunesh.whatsappstatusdownloader.Utilities.source
import java.util.*
import kotlin.collections.ArrayList
import android.os.StrictMode
import com.example.sunesh.whatsappstatusdownloader.Downloads.downloads
import com.example.sunesh.whatsappstatusdownloader.Utilities.destination
import es.dmoral.toasty.Toasty

private var permissionState = true


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)







//        if (ContextCompat.checkSelfPermission(this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//
//
//            // No explanation needed, we can request the permission.
//            ActivityCompat.requestPermissions(this,
//                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                    100)
//        }
//
//
//
//
//
//
//
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){




            val path = Environment.getExternalStorageDirectory().absolutePath+ source
            try {


                val sortedfiles= allfilesorted(path)



                val adapter = adapterClass(this,sortedfiles)
                rcyrview.adapter= adapter
                val layoutManager = LinearLayoutManager(this)
                rcyrview.layoutManager = layoutManager
                rcyrview.setHasFixedSize(false)



            } catch (e: Exception){
                notfound.visibility= View.VISIBLE

            }


        }else{
            srl.isEnabled = false
            permission.visibility= View.VISIBLE
            permissionText.visibility=View.VISIBLE
        }
        permission.setOnClickListener {
            startActivity(Intent(this,splashActivity::class.java))
        }
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        rcyrview.isNestedScrollingEnabled=false

        srl.setOnRefreshListener {
            if (permissionState==true) {
                val files = allfilesorted(Environment.getExternalStorageDirectory().absolutePath + source)
                val adapter = adapterClass(this, files)
                rcyrview.adapter = adapter
                srl.setRefreshing(false);
            }

        }

        downloadsbtn.setOnClickListener{
            val file = File(Environment.getExternalStorageDirectory().absolutePath+ destination)
            val list = file.listFiles()
            if(list.count()==0){
                Toasty.error(this,"No downloads found!!",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this,downloads::class.java)
                intent.putExtra("files",list)
                startActivity(intent)

            }



        }

    }


}

fun allfilesorted(path: String): Array<File>{
    val f= File(path)
    val files =f.listFiles()
    for (i in 0 until  files.count()-1) {
        for (j in 0 until (files.count() - i - 1)) {
            if (files[j].lastModified() < files[j + 1].lastModified()) {
                files[j] = files[j + 1].also { files[j + 1] = files[j] }
            }

        }
    }
    return  files

}


