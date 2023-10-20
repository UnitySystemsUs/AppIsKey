package com.example.appiskey.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding
import com.example.appiskey.photolisting.presenter.dialog.CommonMessageDialog

abstract class BaseFragment<VB : ViewBinding> : Fragment(), LifecycleObserver {

    private var _binding: VB? = null
    protected val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = setupViewBinding(inflater, container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(this)
        observeUiStates()
        setupView()
    }

    abstract fun setupView()

    abstract fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): VB

    abstract fun observeUiStates()

    fun showCommonMessageDialog(
        title: String? = null,
        message: String? = null,
        txtActionPositive: String? = null,
        txtActionNegative: String? = null,
        onBtnPositiveClick: () -> Unit = {},
        onBtnNegativeClick: () -> Unit = {},
    ) {
        activity?.supportFragmentManager?.let {
            CommonMessageDialog.newInstance(
                title = title,
                message = message,
                txtActionPositive = txtActionPositive,
                txtActionNegative = txtActionNegative,
                onBtnPositiveClick = onBtnPositiveClick,
                onBtnNegativeClick = onBtnNegativeClick,
            ).show(it, CommonMessageDialog.TAG)
        } ?: let {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}