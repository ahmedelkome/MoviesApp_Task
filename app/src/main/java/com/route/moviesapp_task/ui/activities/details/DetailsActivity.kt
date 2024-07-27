package com.route.moviesapp_task.ui.activities.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.route.data.utils.Constants
import com.route.domain.models.popular.Popular
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
        when(dataType){
            Constants.POPULAR->{
                val popular = intent.getParcelableExtra<Popular>(Constants.DATA)!!
                binding.popular = popular
            }
        }
    }

    override fun getLayout(): Int = R.layout.activity_details
}