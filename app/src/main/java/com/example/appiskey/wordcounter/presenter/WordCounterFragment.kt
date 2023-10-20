package com.example.appiskey.wordcounter.presenter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.appiskey.R
import com.example.appiskey.base.BaseFragment
import com.example.appiskey.databinding.FragmentWordCounterBinding
import com.example.appiskey.utils.ResUtil

class WordCounterFragment : BaseFragment<FragmentWordCounterBinding>() {
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentWordCounterBinding.inflate(inflater)

    override fun setupView() {
        mBinding.layoutCounterHeader.tvTitle.text = getString(R.string.title_counter)
        mBinding.layoutCounterHeader.ivBack.visibility = View.VISIBLE
        mBinding.layoutCounterHeader.ivBack.setOnClickListener { findNavController().navigateUp() }

        mBinding.btnCount.setOnClickListener {
            val inputStr = mBinding.etInputStr.text.toString()
            if (inputStr.isNotEmpty())
                countWords(inputStr)
            else
                showCommonMessageDialog(message = getString(R.string.error_empty_input))
        }
    }

    private fun countWords(input: String) {
        val result = ResUtil.charCount(input)
        Log.d("MainActivity", "Total Words: ${result["total_words"]}\nCount / Word:")
        for (str in result.keys)
            Log.d("MainActivity", "$str ${result[str]}")
    }

    override fun observeUiStates() {
        Log.d("Word Counter", "observeUiStates")
    }

}