package com.example.sunesh.whatsappstatusdownloader.Downloads

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.sunesh.whatsappstatusdownloader.Adapter.adapterClass
import com.example.sunesh.whatsappstatusdownloader.Controller.MainActivity
import com.example.sunesh.whatsappstatusdownloader.Adapter.downloadAdapter.downloadAdapter
import com.example.sunesh.whatsappstatusdownloader.R
import com.example.sunesh.whatsappstatusdownloader.Utilities.destination
import com.example.sunesh.whatsappstatusdownloader.Utilities.source
import kotlinx.android.synthetic.main.activity_downloads.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class downloads : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloads)

        val path= Environment.getExternalStorageDirectory().absolutePath+ destination
        val f= File(path)
        val files =f.listFiles()
        for (i in 0 until  files.count()-1) {
            for (j in 0 until (files.count() - i - 1)) {
                if (files[j].lastModified() < files[j + 1].lastModified()) {
                    files[j] = files[j + 1].also { files[j + 1] = files[j] }
                }

            }
        }
        val adapter = downloadAdapter(this, files)

        rcyrview1.adapter= adapter
        val layoutManager = LinearLayoutManager(this)
        rcyrview1.layoutManager = layoutManager
        rcyrview1.setHasFixedSize(false)

    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this,MainActivity::class.java))
//    }
}
