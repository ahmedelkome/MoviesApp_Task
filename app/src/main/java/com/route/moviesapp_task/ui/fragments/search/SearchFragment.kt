package com.route.moviesapp_task.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.route.domain.models.search.Search
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.fragment.BaseFragment
import com.route.moviesapp_task.databinding.FragmentSearchBinding
import com.route.moviesapp_task.ui.fragments.popular.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val searchViewModel: SearchViewModel by viewModels<SearchViewModel>()
    lateinit var adapter: SearchAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        binding.lifecycleOwner = this
        searchMovies()
        observeLiveData()
    }

    private fun searchMovies() {
        binding.searchBar.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.getSearchMovies(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun initRecycler() {
        adapter = SearchAdapter(listOf())
        binding.rvSearch.adapter = adapter
    }

    override fun getLayout(): Int = R.layout.fragment_search
    override fun observeLiveData() {
        searchViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        searchViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(
                title = it.title,
                message = it.message,
                posTitle = it.posTitle,
                posClick = it.posClick,
                negaTitle = it.negaTitle,
                negaClick = it.negaClick
            )
        }
        searchViewModel.listOfSearchMovies.observe(viewLifecycleOwner) {
            bindListOfSearch(it)
        }
    }

    private fun bindListOfSearch(list: List<Search>) {
        adapter.updateList(list)
        binding.rvSearch.adapter = adapter
    }
}