package com.route.moviesapp_task.ui.fragments.top_rated


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.route.data.utils.Constants
import com.route.domain.models.toprated.TopRated
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.fragment.BaseFragment
import com.route.moviesapp_task.databinding.FragmentTopRatedBinding
import com.route.moviesapp_task.ui.activities.details.DetailsActivity
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
        adapter = TopRatedAdapter(listOf()){topRated,position->

        }
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
        adapter.onTopRateClicked = {topRated,position->
            val intent = Intent(requireActivity(),DetailsActivity::class.java)
            intent.putExtra(Constants.DATATYPE,Constants.TOPRATED)
            intent.putExtra(Constants.DATA,topRated)
            startActivity(intent)
        }
        binding.rvTopRated.adapter = adapter
    }

    override fun getLayout(): Int = R.layout.fragment_top_rated
}