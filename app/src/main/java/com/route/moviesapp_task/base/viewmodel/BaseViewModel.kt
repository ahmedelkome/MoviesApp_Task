package com.route.moviesapp_task.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.moviesapp_task.utils.model.ErrorMessage

class BaseViewModel : ViewModel() {
    var loadingLiveData = MutableLiveData<Boolean>(false)
    var errorLiveData = MutableLiveData<ErrorMessage>()
}