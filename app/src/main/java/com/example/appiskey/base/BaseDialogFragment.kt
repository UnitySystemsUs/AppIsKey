package com.example.appiskey.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.appiskey.photolisting.presenter.dialog.CommonMessageDialog

const val TAG = "BaseDialogFragment"

abstract class BaseDialogFragment : DialogFragment() {

    abstract fun getLayoutResId(): Int
    abstract fun setDialogStyle()
    abstract fun initializedDialogContent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDialogStyle()
    }

    open fun setupView() {

    }

    fun showCommonMessageDialog(msg: String) {
        if (msg.isEmpty()) return
        activity?.supportFragmentManager?.let {
            CommonMessageDialog.newInstance(msg).show(it, CommonMessageDialog.TAG)
        } ?: let {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgressDialog(show: Boolean) {
        if (show) {
            if (dialog?.isShowing == false) dialog.apply {
                show(childFragmentManager, TAG)
            }
        } else if (dialog?.isShowing == true) dialog?.dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        logLifeCycle("onCreateView")
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logLifeCycle("onViewCreated")
        initializedDialogContent()
    }

    override fun onStart() {
        super.onStart()
        logLifeCycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifeCycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifeCycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifeCycle("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logLifeCycle("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        //To resolve a deeplinking bug when baseDialogFragment is destroyed but progress dialog keep showing
        showProgressDialog(false)
        logLifeCycle("onDestroy")
    }

    private fun logLifeCycle(lifeCycle: String) {
        Log.d("BaseFragmentDialog", getSimpleName() + ":" + lifeCycle)
    }

    fun getSimpleName(): String {
        return this.javaClass.simpleName
    }

}