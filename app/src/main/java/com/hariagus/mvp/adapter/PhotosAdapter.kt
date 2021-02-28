package com.hariagus.mvp.adapter

import com.hariagus.mvp.R
import com.hariagus.mvp.models.PhotosResponse
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_photos_list.view.*

class PhotosAdapter(private val data: List<PhotosResponse>) : Item<ViewHolder>() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val itemView = viewHolder.itemView

        itemView.tvAlbumId.text = data[position].albumId.toString()
        itemView.tvId.text = data[position].id.toString()
        itemView.tvTitle.text = data[position].title
        itemView.tvUrlPhoto.text = data[position].url

        Picasso.get().load(data[position].thumbnailUrl).into(itemView.ivPhoto)
    }

    override fun getLayout(): Int {
        return R.layout.item_photos_list
    }

}