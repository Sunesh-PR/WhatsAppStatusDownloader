package com.example.sunesh.whatsappstatusdownloader.HelpPackage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sunesh.whatsappstatusdownloader.R
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.content.pm.PackageManager





class Page1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val v: View = inflater!!.inflate(R.layout.helpacitivity1, container, false)
            val button = v.findViewById<Button>(R.id.whatsappbtn)
            button.setOnClickListener {

                val pm = context?.getPackageManager()
                val appStartIntent = pm?.getLaunchIntentForPackage("com.whatsapp")
                if (null != appStartIntent) {
                    context?.startActivity(appStartIntent)
                }
            }
            return v
        }
}