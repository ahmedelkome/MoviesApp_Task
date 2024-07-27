package com.route.moviesapp_task.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.moviesapp_task.utils.model.ErrorMessage

open class BaseViewModel : ViewModel() {
    var loadingLiveData = MutableLiveData<Boolean>()
    var errorLiveData = MutableLiveData<ErrorMessage>()
}