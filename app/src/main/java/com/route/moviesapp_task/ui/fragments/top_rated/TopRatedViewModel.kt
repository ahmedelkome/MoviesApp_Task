package com.route.moviesapp_task.ui.fragments.top_rated

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.route.domain.common.ResultWrapper
import com.route.domain.models.toprated.TopRated
import com.route.domain.usecases.toprated_usecase.TopRatedUseCase
import com.route.moviesapp_task.base.viewmodel.BaseViewModel
import com.route.moviesapp_task.utils.model.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel
@Inject constructor(
    private val topRatedUseCase: TopRatedUseCase
) : BaseViewModel() {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    var listOfTopRated = MutableLiveData<List<TopRated>>()
    fun getTopRatedList() {
        viewModelScope.launch(dispatcher) {
            loadingLiveData.postValue(true)
            topRatedUseCase.getTopRatedMovies().collect {
                when (it) {
                    is ResultWrapper.Failure -> {
                        errorLiveData.postValue(
                            ErrorMessage(
                                title = "Error",
                                message = it.e.localizedMessage
                            )
                        )
                        loadingLiveData.postValue(false)
                    }

                    ResultWrapper.Loading -> {
                        loadingLiveData.postValue(true)
                    }

                    is ResultWrapper.Success -> {
                        listOfTopRated.postValue(it.data)
                        loadingLiveData.postValue(false)
                    }
                }
            }
        }
    }
}