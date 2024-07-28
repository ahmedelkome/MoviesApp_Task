package com.route.moviesapp_task.ui.activities.home

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.activity.BaseActivity
import com.route.moviesapp_task.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTabs()
        initFragmentNavGraph()
        initSearch()
    }

    private fun initSearch() {
        binding.searchIcon.setOnClickListener {
            val currentFragmentId = getCurrentFragmentId()

            // Determine which action to use based on the current fragment
            val action = when (currentFragmentId) {
                R.id.popularFragment -> R.id.action_popularFragment_to_searchFragment
                R.id.topRatedFragment -> R.id.action_topRatedFragment_to_searchFragment
                else -> null
            }

            // Navigate using the appropriate action if defined
            action?.let {
                navController.navigate(it)
                visibleTabLayout(visible = false)
            }

        }
    }

    private fun visibleTabLayout(visible: Boolean) {
        if (visible) {
            binding.tabLayout.isVisible = visible
        } else if (!visible) {
            binding.tabLayout.isVisible = visible
        }
    }

    private fun initTabs() {
        val popularTab = binding.tabLayout.newTab()
        val topRatedTap = binding.tabLayout.newTab()
        popularTab.setText("Popular")
        topRatedTap.setText("TopRated")
        binding.tabLayout.addTab(popularTab)
        binding.tabLayout.addTab(topRatedTap)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        navController.navigate(R.id.action_topRatedFragment_to_popularFragment)
                    }

                    1 -> navController.navigate(R.id.action_popularFragment_to_topRatedFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
        binding.tabLayout.getTabAt(0)?.select()

    }

    override fun getLayout(): Int = R.layout.activity_home

    private fun initFragmentNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun getCurrentFragmentId(): Int? {
        return navController.currentDestination?.id
    }


    override fun onBackPressed() {
        super.onBackPressed()
        visibleTabLayout(true)
    }
}