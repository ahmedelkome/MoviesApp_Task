package com.route.moviesapp_task.ui.fragments.popular


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.route.domain.models.popular.Popular
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.fragment.BaseFragment
import com.route.moviesapp_task.databinding.FragmentPopularBinding
import com.route.moviesapp_task.ui.fragments.popular.adapter.PopularAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>() {
    private val popularViewModel: PopularViewModel by viewModels<PopularViewModel>()
    lateinit var adapter: PopularAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        popularViewModel.getPopularMovies()
        binding.lifecycleOwner = this
        observeLiveData()
    }

    private fun bindListOfPopular(listOfPopular: List<Popular>) {
        adapter.updateList(listOfPopular)
        binding.rvPopular.adapter = adapter
    }

    private fun initRecycler() {
        adapter = PopularAdapter(listOf())
        binding.rvPopular.adapter = adapter
    }

    override fun observeLiveData() {
        popularViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        popularViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(
                title = it.title,
                message = it.message,
                posTitle = it.posTitle,
                posClick = it.posClick,
                negaTitle = it.negaTitle,
                negaClick = it.negaClick
            )
        }
        popularViewModel.popularList.observe(viewLifecycleOwner) {
            bindListOfPopular(it)
        }
    }


    override fun getLayout(): Int = R.layout.fragment_popular
}