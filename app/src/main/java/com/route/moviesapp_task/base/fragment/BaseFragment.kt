package com.route.moviesapp_task.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.route.moviesapp_task.R
import com.route.moviesapp_task.base.viewmodel.BaseViewModel

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    lateinit var binding: DB
    var dialog: AlertDialog? = null
    private val viewModel: BaseViewModel by viewModels<BaseViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        observeLiveData()
    }

    abstract fun getLayout(): Int

    open fun observeLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
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

    private fun showLoading() {
        dialog = AlertDialog.Builder(requireActivity())
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
        val dialogError = AlertDialog.Builder(requireActivity())
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