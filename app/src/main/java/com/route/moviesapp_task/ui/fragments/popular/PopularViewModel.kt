package com.route.moviesapp_task.ui.fragments.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.route.domain.common.ResultWrapper
import com.route.domain.models.popular.Popular
import com.route.domain.usecases.popular_usecase.PopularUseCase
import com.route.moviesapp_task.base.viewmodel.BaseViewModel
import com.route.moviesapp_task.utils.model.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: PopularUseCase
) : BaseViewModel() {
    var popularList = MutableLiveData<List<Popular>>()

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingLiveData.postValue(true)
            popularUseCase.getPopularMovies().collect {
                when (it) {
                    is ResultWrapper.Failure -> {
                        loadingLiveData.postValue(false)
                        errorLiveData.postValue(
                            ErrorMessage(
                                title = "Error",
                                message = it.e.localizedMessage
                            )
                        )
                    }

                    ResultWrapper.Loading -> {
                        loadingLiveData.postValue(true)
                    }

                    is ResultWrapper.Success -> {
                        loadingLiveData.postValue(false)
                        popularList.postValue(it.data)
                    }
                }
            }
        }
    }
}