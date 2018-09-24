package com.example.sunesh.whatsappstatusdownloader.HelpPackage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class helpAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return Page1()
        } else {
            return Page2()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}
