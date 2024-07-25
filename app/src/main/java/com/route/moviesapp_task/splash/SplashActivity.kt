package com.route.moviesapp_task.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.route.moviesapp_task.databinding.ActivitySplashBinding
import com.route.moviesapp_task.ui.activities.HomeActivity

class SplashActivity : AppCompatActivity() {
    private var _binding: ActivitySplashBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()
        navigateToHome()
    }

    private fun navigateToHome() {
        Handler(mainLooper).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}