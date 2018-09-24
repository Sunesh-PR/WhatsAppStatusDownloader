package com.example.sunesh.whatsappstatusdownloader.HelpPackage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sunesh.whatsappstatusdownloader.R
import kotlinx.android.synthetic.main.activity_help.*

class helpActivity : AppCompatActivity() {

    lateinit var adapter: helpAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        adapter = helpAdapter(supportFragmentManager)
        view_pager.adapter = adapter
    }
}
