package com.route.moviesapp_task.base.fragment

import android.os.Bundle
import android.util.Log
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
    }

    abstract fun getLayout(): Int

    abstract fun observeLiveData()

    open fun showLoading() {
        val builder = AlertDialog.Builder(requireActivity())
            .setView(R.layout.loading_layout)
        dialog = builder.create()
        dialog?.let { it.show() }
    }

    open fun hideLoading() {
        dialog?.let {
            it.dismiss()
        }
    }

    open fun showError(
        title: String? = null,
        message: String? = null,
        posTitle: String? = null,
        posClick: (() -> Unit)? = null,
        negaTitle: String? = null,
        negaClick: (() -> Unit)? = null

    ) {
        val dialogError = AlertDialog.Builder(requireActivity())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(posTitle) { dialog, int ->
                posClick?.invoke()
            }
            .setNegativeButton(negaTitle) { dialog, int ->
                negaClick?.invoke()
            }
        dialogError.create()
            .show()
    }
}