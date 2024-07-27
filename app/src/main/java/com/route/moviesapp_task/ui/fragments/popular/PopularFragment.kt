package com.route.moviesapp_task.ui.fragments.popular


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.fragment.BaseFragment
import com.route.moviesapp_task.databinding.FragmentPopularBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>() {
    private val popularViewModel:PopularViewModel by viewModels<PopularViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner=this
        popularViewModel.getPopularMovies()
        observeLiveData()
    }

    override fun observeLiveData() {
        super.observeLiveData()
        popularViewModel.popularList.observe(viewLifecycleOwner){

        }
    }
    override fun getLayout(): Int = R.layout.fragment_popular
}