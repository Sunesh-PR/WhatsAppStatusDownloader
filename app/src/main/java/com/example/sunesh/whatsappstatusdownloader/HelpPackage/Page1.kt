package com.example.sunesh.whatsappstatusdownloader.HelpPackage


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sunesh.whatsappstatusdownloader.R

class Page1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val v: View = inflater!!.inflate(R.layout.helpacitivity1, container, false)
            return v
        }
}