package com.example.appiskey.presenter.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.appiskey.R
import com.example.appiskey.base.BaseFragment
import com.example.appiskey.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater)

    override fun setupView() {
        animateImage()
    }

    private fun animateImage() {
        val slideAnimation = AnimationUtils.loadAnimation(
            requireContext(), androidx.appcompat.R.anim.abc_popup_enter
        )
        slideAnimation.duration = 3000
        slideAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                Log.d("Splash", "onAnimationStart")
            }

            override fun onAnimationEnd(p0: Animation?) {
                Log.d("Splash", "onAnimationEnd")
                findNavController().navigate(R.id.action_to_home)
            }

            override fun onAnimationRepeat(p0: Animation?) {
                Log.d("Splash", "onAnimationRepeat")
            }
        })
        mBinding.ivSplash.startAnimation(slideAnimation)
    }

    override fun observeUiStates() {
        Log.d("Splash", "observeUiStates")
    }
}