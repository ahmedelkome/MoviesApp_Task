package com.route.moviesapp_task.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.fragment.BaseFragment
import com.route.moviesapp_task.databinding.FragmentSearchBinding


class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val searchViewModel : SearchViewModel by viewModels<SearchViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        observeLiveData()
    }
    override fun getLayout(): Int = R.layout.fragment_search
    override fun observeLiveData() {
        searchViewModel.loadingLiveData.observe(viewLifecycleOwner){
            if (it==true){
                showLoading()
            }else{
                hideLoading()
            }
        }
        searchViewModel.errorLiveData.observe(viewLifecycleOwner){
            showError(
                title = it.title,
                message = it.message,
                posTitle = it.posTitle,
                posClick = it.posClick,
                negaTitle = it.negaTitle,
                negaClick = it.negaClick
            )
        }
        searchViewModel.listOfSearchMovies.observe(viewLifecycleOwner){
            
        }
    }
}