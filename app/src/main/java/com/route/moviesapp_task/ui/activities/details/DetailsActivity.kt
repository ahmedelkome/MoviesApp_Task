package com.route.moviesapp_task.ui.activities.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.route.data.utils.Constants
import com.route.domain.models.popular.Popular
import com.route.domain.models.search.Search
import com.route.domain.models.toprated.TopRated
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.activity.BaseActivity
import com.route.moviesapp_task.databinding.ActivityDetailsBinding

class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.lifecycleOwner = this
        checkTypeOfData()
    }

    private fun checkTypeOfData() {
        val dataType = intent.getStringExtra(Constants.DATATYPE)
        when (dataType) {
            Constants.POPULAR -> {
                val popular = intent.getParcelableExtra<Popular>(Constants.DATA)!!
                binding.popular = popular
            }

            Constants.TOPRATED -> {
                val topRated = intent.getParcelableExtra<TopRated>(Constants.DATA)!!
                binding.topRated = topRated
            }

            Constants.SEARCH -> {
                val search = intent.getParcelableExtra<Search>(Constants.SEARCH)
                binding.search = search
            }
        }
    }

    override fun getLayout(): Int = R.layout.activity_details
}