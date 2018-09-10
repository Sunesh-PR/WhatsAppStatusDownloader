package com.example.sunesh.whatsappstatusdownloader.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import com.example.sunesh.whatsappstatusdownloader.R
import java.io.File
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.net.Uri
import android.widget.MediaController
import java.net.URI


class adapterClass( val context: Context,val files : Array<File>): RecyclerView.Adapter<adapterClass.holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val View = LayoutInflater.from(context).inflate(R.layout.adapter_design,parent,false)
        return holder(View)
    }

    override fun getItemCount(): Int {
        return files.count()
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder?.bind(context,files[position])
    }

    inner class holder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        val img = itemView?.findViewById<ImageView>(R.id.img)
        val imgbtn = itemView?.findViewById<Button>(R.id.imgbtn)
        val video = itemView?.findViewById<VideoView>(R.id.video)
        val videobtn = itemView?.findViewById<Button>(R.id.videobtn)

        fun bind(context: Context,file: File){
            if(file.name.endsWith(".mp4")){

                imgbtn?.visibility=View.GONE
                video?.setVideoURI(Uri.parse(file.absolutePath))
                video?.setMediaController(MediaController(context))
                val mediaController = MediaController(context)
                mediaController.setAnchorView(video)
                video?.setMediaController(mediaController)

                video?.setOnPreparedListener { video?.start() }


            }else{
                video?.visibility=View.GONE
                videobtn?.visibility=View.GONE
                val bitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
                img?.setImageBitmap(bitmap)

            }


        }

    }
}