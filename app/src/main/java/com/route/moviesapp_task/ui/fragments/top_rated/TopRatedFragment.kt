package com.route.moviesapp_task.ui.fragments.top_rated


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.fragment.BaseFragment
import com.route.moviesapp_task.databinding.FragmentTopRatedBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopRatedFragment : BaseFragment<FragmentTopRatedBinding>() {

    private val topRatedViewModel: TopRatedViewModel by viewModels<TopRatedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        topRatedViewModel.getTopRatedList()
        observeLiveData()
    }

    override fun observeLiveData() {
        topRatedViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        topRatedViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(
                title = it.title,
                message = it.message,
                posTitle = it.posTitle,
                posClick = it.posClick,
                negaTitle = it.negaTitle,
                negaClick = it.negaClick
            )
        }
        topRatedViewModel.listOfTopRated.observe(viewLifecycleOwner){
            
        }
    }

    override fun getLayout(): Int = R.layout.fragment_top_rated
}