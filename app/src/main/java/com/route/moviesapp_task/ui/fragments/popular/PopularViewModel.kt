package com.route.moviesapp_task.ui.fragments.popular

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.route.domain.common.ResultWrapper
import com.route.domain.models.popular.Popular
import com.route.domain.usecases.popular_usecase.PopularUseCase
import com.route.moviesapp_task.base.viewmodel.BaseViewModel
import com.route.moviesapp_task.utils.model.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: PopularUseCase
) : BaseViewModel() {

    var popularList = MutableLiveData<List<Popular>>()
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO


    fun getPopularMovies() {
        loadingLiveData.postValue(true)
        viewModelScope.launch(dispatcher) {
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
                        popularList.postValue(it.data)
                        loadingLiveData.postValue(false)
                    }
                }
            }
        }
    }
}