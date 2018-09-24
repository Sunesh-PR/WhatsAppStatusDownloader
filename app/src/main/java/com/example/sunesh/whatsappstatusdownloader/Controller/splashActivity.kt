package com.example.sunesh.whatsappstatusdownloader.Controller

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.sunesh.whatsappstatusdownloader.R

private const val PERMISSION_REQUEST = 10
class splashActivity : AppCompatActivity() {
    private lateinit var context: Context
    private var permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000


    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        context= this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(context, permissions)) {
                Toast.makeText(context, "Permission are already provided", Toast.LENGTH_SHORT).show()
                mDelayHandler = Handler()
                mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
            } else {
                requestPermissions(permissions, PERMISSION_REQUEST)
            }
        }else{
            Toast.makeText(context, "Permission are already provided", Toast.LENGTH_SHORT).show()
        }





    }
    fun checkPermission(context: Context, permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices){
            if(checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_REQUEST){
            var allSuccess = true
            for(i in permissions.indices){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    allSuccess = false
                    var requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
                    if(requestAgain){
                        Toast.makeText(context,"Permission denied",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Go to settings and enable the permission",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if(allSuccess)
                Toast.makeText(context,"Permissions Granted",Toast.LENGTH_SHORT).show()
            mDelayHandler = Handler()
            mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)


        }
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}