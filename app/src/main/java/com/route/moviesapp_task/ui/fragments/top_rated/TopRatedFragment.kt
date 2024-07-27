package com.route.moviesapp_task.ui.fragments.top_rated


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.route.domain.models.toprated.TopRated
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.fragment.BaseFragment
import com.route.moviesapp_task.databinding.FragmentTopRatedBinding
import com.route.moviesapp_task.ui.fragments.popular.adapter.TopRatedAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopRatedFragment : BaseFragment<FragmentTopRatedBinding>() {

    private val topRatedViewModel: TopRatedViewModel by viewModels<TopRatedViewModel>()
    lateinit var adapter: TopRatedAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        binding.lifecycleOwner = this
        topRatedViewModel.getTopRatedList()
        observeLiveData()
    }

    private fun initRecycler() {
        adapter = TopRatedAdapter(listOf())
        binding.rvTopRated.adapter = adapter
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
        topRatedViewModel.listOfTopRated.observe(viewLifecycleOwner) {
            bindListOfTopRated(it)
        }
    }

    private fun bindListOfTopRated(list: List<TopRated>) {
        adapter.updateList(list)
        binding.rvTopRated.adapter = adapter
    }

    override fun getLayout(): Int = R.layout.fragment_top_rated
}