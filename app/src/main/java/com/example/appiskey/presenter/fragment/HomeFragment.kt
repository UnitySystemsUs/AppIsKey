package com.example.appiskey.presenter.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.appiskey.R
import com.example.appiskey.base.BaseFragment
import com.example.appiskey.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(), View.OnClickListener {
    override fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater)

    override fun setupView() {
        mBinding.layoutHomeHeader.tvTitle.text = getString(R.string.title_home)

        mBinding.btnCountWords.setOnClickListener(this)
        mBinding.btnViewListing.setOnClickListener(this)
    }

    override fun observeUiStates() {
        Log.d("Home", "observeUiStates")
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnCountWords -> findNavController().navigate(R.id.action_to_wordCounterFragment)
            R.id.btnViewListing -> findNavController().navigate(R.id.action_to_photoListing)
        }
    }
}