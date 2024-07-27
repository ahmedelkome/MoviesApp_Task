package com.route.moviesapp_task.ui.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.route.domain.common.ResultWrapper
import com.route.domain.models.search.Search
import com.route.domain.usecases.search_usecase.SearchUseCase
import com.route.moviesapp_task.base.viewmodel.BaseViewModel
import com.route.moviesapp_task.utils.model.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
):BaseViewModel() {
    var listOfSearchMovies = MutableLiveData<List<Search>>()
    private val dispatcher = Dispatchers.IO
    fun getSearchMovies(search:String){
        viewModelScope.launch (dispatcher){
            loadingLiveData.postValue(true)
            searchUseCase.getSearchMovies(search).collect{
                when(it){
                    is ResultWrapper.Failure -> {
                        errorLiveData.postValue(ErrorMessage(
                            title = "Error",
                            message = it.e.localizedMessage
                        ))
                        loadingLiveData.postValue(false)
                    }
                    ResultWrapper.Loading -> {
                        loadingLiveData.postValue(true)
                    }
                    is ResultWrapper.Success -> {
                        listOfSearchMovies.postValue(it.data)
                        loadingLiveData.postValue(false)
                    }
                }
            }
        }
    }
}