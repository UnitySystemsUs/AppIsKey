package com.example.appiskey.photolisting.presenter.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appiskey.R
import com.example.appiskey.base.BaseFragment
import com.example.appiskey.databinding.FragmentPreviewBinding

class PreviewFragment : BaseFragment<FragmentPreviewBinding>() {

    companion object {
        const val PREVIEW_IMG_URL = "preview_img_url"
    }

    private var previewImgUrl: String? = null

    override fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentPreviewBinding.inflate(inflater)

    override fun setupView() {
        arguments?.let {
            previewImgUrl = it.getString(PREVIEW_IMG_URL)
        }

        mBinding.layoutPreviewHeader.tvTitle.text = getString(R.string.title_preview)

        mBinding.layoutPreviewHeader.ivBack.visibility = View.VISIBLE
        mBinding.layoutPreviewHeader.ivBack.setOnClickListener { findNavController().navigateUp() }

        Log.d("PreviewFragment", "Preview Img Url: $previewImgUrl")
        previewImgUrl?.let {
            mBinding.ivPreview.visibility = View.VISIBLE
            mBinding.pbPreviewLoader.visibility = View.GONE
            Glide.with(requireContext()).load(it).into(mBinding.ivPreview)
        }
    }

    override fun observeUiStates() {
        Log.d("PreviewFragment", " observeUiStates : NOt used")
    }

}