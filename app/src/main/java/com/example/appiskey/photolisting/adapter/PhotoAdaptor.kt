package com.example.appiskey.photolisting.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.appiskey.databinding.LayoutPhotoItemBinding
import com.example.appiskey.photolisting.model.PixabayPhotoModel
import com.example.appiskey.utils.stringToArrayList

class PhotoAdaptor(var callBack: (pixabayPictureModel: PixabayPhotoModel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var pixabayList: ArrayList<PixabayPhotoModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutPhotoItemBinding.inflate(inflater, parent, false)
        return PhotoItemVH(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoItemVH).bind(pixabayList[position])
    }

    override fun getItemCount(): Int {
        return pixabayList.size
    }

    fun setPhotosList(pixabayList: ArrayList<PixabayPhotoModel>) {
        this.pixabayList = pixabayList
        notifyDataSetChanged()
    }

    private inner class PhotoItemVH constructor(private var mBinding: LayoutPhotoItemBinding) :
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener {

        private lateinit var tagsAdaptor: TagsAdaptor

        init {
            mBinding.root.setOnClickListener(this)
        }

        fun bind(item: PixabayPhotoModel) {
            item.largeImageURL?.let {
                Glide.with(mBinding.ivPhoto.context).load(it)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            mBinding.cvPhoto.visibility = View.VISIBLE
                            mBinding.pbPhotoLoader.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            mBinding.cvPhoto.visibility = View.VISIBLE
                            mBinding.pbPhotoLoader.visibility = View.GONE
                            return false
                        }
                    }).into(mBinding.ivPhoto)
            }
            item.user?.let {
                mBinding.tvUserName.text = it
            }
            item.likes?.let {
                mBinding.tvLikes.text = "$it"
            }
            item.tags?.let {
                tagConfig()
                tagsAdaptor.setTagsList(mBinding.rcvTags.context.stringToArrayList(it))
            }

        }

        private fun tagConfig() {
            tagsAdaptor = TagsAdaptor()
            mBinding.rcvTags.layoutManager =
                LinearLayoutManager(mBinding.rcvTags.context, RecyclerView.HORIZONTAL, false)
            mBinding.rcvTags.adapter = tagsAdaptor
        }

        override fun onClick(clicekdView: View?) {
            callBack(pixabayList[adapterPosition])
        }
    }
}