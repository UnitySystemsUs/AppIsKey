package com.example.appiskey.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appiskey.R
import com.example.appiskey.adapter.PhotoAdaptor
import com.example.appiskey.base.BaseFragment
import com.example.appiskey.databinding.FragmentPhotoListingBinding
import com.example.appiskey.model.PixabayPhotoModel
import com.example.appiskey.presenter.viewmodel.HomeViewModel
import com.example.appiskey.uistate.HomeUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListingFragment : BaseFragment<FragmentPhotoListingBinding>(),
    SwipeRefreshLayout.OnRefreshListener {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var pixabayPhotoList: ArrayList<PixabayPhotoModel>

    private val photoAdaptor: PhotoAdaptor by lazy {
        PhotoAdaptor {
            val bundle = Bundle()
            bundle.putString(PreviewFragment.PREVIEW_IMG_URL, it.largeImageURL)
            findNavController().navigate(R.id.action_to_preview, bundle)
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentPhotoListingBinding.inflate(inflater)

    override fun setupView() {
        mBinding.layoutHomeHeader.tvTitle.text = getString(R.string.title_listing)
        mBinding.layoutHomeHeader.ivBack.visibility = View.VISIBLE
        mBinding.layoutHomeHeader.ivBack.setOnClickListener { findNavController().navigateUp() }

        mBinding.swipeRefreshHome.setOnRefreshListener(this)
        mBinding.swipeRefreshHome.setColorSchemeResources(
            R.color.purple_5341c7,
            R.color.purple_5341c7,
            R.color.purple_5341c7,
            R.color.purple_5341c7
        )
        if (!::pixabayPhotoList.isInitialized || pixabayPhotoList.isEmpty()) homeViewModel.hitApi(
            requireContext(),
            hashMapOf()
        )
        setupRCV()
    }

    override fun observeUiStates() {
        homeViewModel.homeUiState.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { state ->
                when (state) {
                    is HomeUiState.NoNetwork -> showCommonMessageDialog(
                        message = getString(R.string.internet_connection_issue)
                    )

                    is HomeUiState.ShowProgress -> {
                        mBinding.swipeRefreshHome.isRefreshing = true
                    }

                    is HomeUiState.HitResponse -> {
                        mBinding.swipeRefreshHome.isRefreshing = false
                        state.pixabayApiResponse.let { response ->
                            response.pixabayPhotoList?.let { photoList ->
                                if (!::pixabayPhotoList.isInitialized) pixabayPhotoList =
                                    ArrayList()
                                pixabayPhotoList = photoList
                                photoAdaptor.setPhotosList(photoList)

                            }
                        }
                    }

                    is HomeUiState.Error -> {
                        mBinding.swipeRefreshHome.isRefreshing = false

                        showCommonMessageDialog(
                            message = state.errorResponse?.message
                        )
                    }
                }
            }
        }
    }

    override fun onRefresh() {
        homeViewModel.hitApi(requireContext(), hashMapOf())
    }

    private fun setupRCV() {
        mBinding.rcvHitItem.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        mBinding.rcvHitItem.adapter = photoAdaptor
    }
}