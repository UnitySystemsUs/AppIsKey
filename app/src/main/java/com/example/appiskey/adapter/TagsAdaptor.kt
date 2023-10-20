package com.example.appiskey.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appiskey.databinding.ItemTagBinding

class TagsAdaptor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tagsList: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTagBinding.inflate(inflater, parent, false)
        return TagVH(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TagVH).bind(tagsList[position])
    }

    override fun getItemCount(): Int {
        return tagsList.size
    }

    fun setTagsList(tagsList: ArrayList<String>) {
        this.tagsList = tagsList
        notifyDataSetChanged()
    }

    private inner class TagVH constructor(private var mBinding: ItemTagBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: String) {
            item.let {
                mBinding.tvTag.text = it
            }

        }
    }
}