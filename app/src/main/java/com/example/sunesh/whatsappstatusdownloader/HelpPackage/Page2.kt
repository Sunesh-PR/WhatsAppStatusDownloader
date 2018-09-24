package com.example.sunesh.whatsappstatusdownloader.HelpPackage

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sunesh.whatsappstatusdownloader.Controller.MainActivity
import com.example.sunesh.whatsappstatusdownloader.R

class Page2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v:View= inflater!!.inflate(R.layout.helpactivity2, container, false)
        val button = v.findViewById<Button>(R.id.donwloadfrmhere)
        button.setOnClickListener {
            val context = context
            startActivity(Intent(context,MainActivity::class.java))

        }
        return v
    }





}