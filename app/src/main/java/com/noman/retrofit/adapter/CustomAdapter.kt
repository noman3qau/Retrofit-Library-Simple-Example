package com.noman.retrofit.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.picasso.OkHttp3Downloader
import com.noman.retrofit.R
import com.noman.retrofit.models.DataPhoto
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.Builder

class CustomAdapter(var photoList: ArrayList<DataPhoto>, var mContext: Context) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.photo_list_item, parent, false)
        return CustomViewHolder(view)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {


        holder.titleLabel.text = photoList.get(position).title

        var builder: Picasso.Builder = Picasso.Builder(mContext)
        builder.downloader(OkHttp3Downloader(mContext));

        builder.build().load(photoList.get(position).thumbnailUrl)
                .placeholder(R.drawable.dummy_image)
                .error(R.drawable.dummy_image)
                .into(holder.photoImage)


    }

    override fun getItemCount(): Int {
        return 50
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val photoImage = itemView.findViewById<AppCompatImageView>(R.id.photoImage)
        val titleLabel = itemView.findViewById<AppCompatTextView>(R.id.titleLabel)

    }


}