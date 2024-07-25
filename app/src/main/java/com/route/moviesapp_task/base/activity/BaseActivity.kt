package com.route.moviesapp_task.base.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.viewmodel.BaseViewModel

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: DB
    var dialog: AlertDialog? = null
    private val viewModel: BaseViewModel by viewModels<BaseViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this
        observeLiveData()
    }

    open fun observeLiveData() {
        viewModel.loadingLiveData.observe(this) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.errorLiveData.observe(this) {
            showError(
                title = it.title!!,
                message = it.message!!,
                posTitle = it.posTitle!!,
                posClick = it.posClick!!,
                negaTitle = it.negaTitle!!,
                negaClick = it.negaClick!!
            )
        }
    }

    abstract fun getLayout(): Int

    private fun showLoading() {
        dialog = AlertDialog.Builder(this)
            .setView(R.layout.loading_layout)
            .create()
        dialog?.let { it.show() }
    }

    private fun hideLoading() {
        dialog?.let {
            it.dismiss()
        }
    }

    private fun showError(
        title: String,
        message: String,
        posTitle: String,
        posClick: () -> Unit,
        negaTitle: String,
        negaClick: () -> Unit

    ) {
        val dialogError = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(posTitle) { dialog, int ->
                posClick.invoke()
            }
            .setNegativeButton(negaTitle) { dialog, int ->
                negaClick.invoke()
            }
            .create()
        dialogError.show()
    }
}