package com.example.sunesh.whatsappstatusdownloader.Adapter.downloadAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sunesh.whatsappstatusdownloader.R
import com.example.sunesh.whatsappstatusdownloader.Utilities.destination
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat

class downloadAdapter(val context: Context, val files : Array<File>): RecyclerView.Adapter<downloadAdapter.holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val View = LayoutInflater.from(context).inflate(R.layout.downloads_adapter_design,parent,false)
        return holder(View)
    }

    override fun getItemCount(): Int {
        return files.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.bind(context,files[position])
    }

    inner class holder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        val img = itemView?.findViewById<ImageView>(R.id.img)
        val video = itemView?.findViewById<VideoView>(R.id.videoView)
        val sharebtn = itemView?.findViewById<Button>(R.id.sharebtn)
        val time = itemView?.findViewById<TextView>(R.id.time)
        val frame = itemView?.findViewById<FrameLayout>(R.id.frame)




        @SuppressLint("SimpleDateFormat")
        fun bind(context: Context, file: File){
            val sdf: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm aa")
            time?.text=sdf.format(file.lastModified())
            sharebtn?.setOnClickListener { share(file, context) }

            if(file.name.endsWith(".mp4")){
                video?.setVideoURI(Uri.parse(file.absolutePath))
                video?.seekTo(100)
                val mc = MediaController(context)
                video?.setMediaController(mc)
                val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                lp.gravity = Gravity.BOTTOM
                mc.setLayoutParams(lp)
                (mc.getParent() as ViewGroup).removeView(mc)
                (frame as FrameLayout).addView(mc)






            }else{
                video?.visibility= View.GONE
                val bitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
                img?.setImageBitmap(bitmap)

            }


        }

    }
}

fun share(file: File, context: Context){
    val intent = Intent(Intent.ACTION_SEND)
    if(file.endsWith(".mp4")){
        intent.setType("video/mp4")
    }else{
        intent.setType("image/jpeg")
    }
    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
    intent.putExtra(Intent.EXTRA_TEXT, "Shared using WhatsApp Status Downloader")
    ContextCompat.startActivity(context, Intent.createChooser(intent, "Share using"), null)
}