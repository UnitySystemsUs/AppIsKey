package com.example.appiskey.presenter.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appiskey.R
import com.example.appiskey.base.BaseDialogFragment
import com.example.appiskey.databinding.DialogCommonErrorBinding

class CommonMessageDialog : BaseDialogFragment(), View.OnClickListener {

    private var mTitle: String? = null
    private var mMessage: String? = null
    private var txtActionPositive: String? = null
    private var txtActionNegative: String? = null
    private lateinit var onBtnPositiveClick: () -> Unit
    private lateinit var onBtnNegativeClick: () -> Unit

    private val mBinding: DialogCommonErrorBinding by lazy {
        DialogCommonErrorBinding.inflate(layoutInflater)
    }

    companion object {
        var TAG = "CommonMessageDialog"
        var ARG_TITLE = "title"
        var ARG_MESSAGE = "message"
        var ARG_ACTION_POSITIVE = "action_positive"
        var ARG_ACTION_NEGATIVE = "action_negative"

        fun newInstance(
            title: String? = null,
            message: String? = null,
            txtActionPositive: String? = null,
            txtActionNegative: String? = null,
            onBtnPositiveClick: () -> Unit = {},
            onBtnNegativeClick: () -> Unit = {},
        ): CommonMessageDialog {
            return CommonMessageDialog().apply {
                this.onBtnPositiveClick = onBtnPositiveClick
                this.onBtnNegativeClick = onBtnNegativeClick
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_MESSAGE, message)
                    putString(ARG_ACTION_POSITIVE, txtActionPositive)
                    putString(ARG_ACTION_NEGATIVE, txtActionNegative)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            mTitle = it.getString(ARG_TITLE)
            mMessage = it.getString(ARG_MESSAGE)
            txtActionPositive = it.getString(ARG_ACTION_POSITIVE)
            txtActionNegative = it.getString(ARG_ACTION_NEGATIVE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = mBinding.root

    override fun getLayoutResId(): Int {
        return R.layout.dialog_common_error
    }

    override fun setDialogStyle() {

    }

    override fun initializedDialogContent() {
        isCancelable = false
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        mBinding.tvErrorMsg.text = mMessage ?: ""

        if (!mTitle.isNullOrEmpty()) {
            mBinding.tvTitle.text = mTitle
            mBinding.tvTitle.visibility = View.VISIBLE
        } else {
            mBinding.tvTitle.visibility = View.GONE
        }

        if (!mMessage.isNullOrEmpty()) {
            mBinding.tvErrorMsg.text = mMessage
            mBinding.tvErrorMsg.visibility = View.VISIBLE
        } else {
            mBinding.tvErrorMsg.visibility = View.GONE
        }

        if (!txtActionPositive.isNullOrEmpty()) {
            mBinding.btnPopupOk.text = txtActionPositive
        }

        if (!txtActionNegative.isNullOrEmpty()) {
            mBinding.btnPopupNo.visibility = View.VISIBLE
            mBinding.btnPopupNo.text = txtActionNegative
        } else
            mBinding.btnPopupNo.visibility = View.GONE
        mBinding.btnPopupOk.setOnClickListener(this)
        mBinding.btnPopupNo.setOnClickListener(this)
    }

    override fun onClick(clickedView: View) {
        when (clickedView) {
            mBinding.btnPopupOk -> {
                dismiss()
                onBtnPositiveClick.invoke()
            }

            mBinding.btnPopupNo -> {
                dismiss()
                onBtnNegativeClick.invoke()
            }
        }
    }

}