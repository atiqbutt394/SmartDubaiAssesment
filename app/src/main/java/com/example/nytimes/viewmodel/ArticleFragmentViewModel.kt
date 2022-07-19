package com.example.nytimes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimes.data.network.api.ResultWrapper
import com.example.nytimes.constant.Constants
import com.example.nytimes.articlelist.ArticleNavigator
import com.example.nytimes.ui.activity.bindngadapter.LoadingState
import com.example.nytimes.data.responses.MostViewedResponse
import com.example.nytimes.data.repository.MainRepository
import com.example.nytimes.data.responses.Results
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ArticleFragmentViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
     val mostViewedLiveData = MutableLiveData<MostViewedResponse>()

    val isLoadingState = MutableLiveData<LoadingState>()

    private val _navigateToArticleDetail = MutableLiveData<Results>()

    val navigateToArticleDetailLiveData get() = _navigateToArticleDetail

    lateinit var articleNavigator: ArticleNavigator

    fun onArticleClicked(resultModel: Results) {
        _navigateToArticleDetail.value = resultModel
    }

    fun setNavigator(articleNavigator: ArticleNavigator) {
        this.articleNavigator = articleNavigator

    }

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        isLoadingState.postValue(LoadingState.LOADED)
        articleNavigator.onError("Something went wrong...please try again")
    }

    fun getMostViewed(mPeriod: String) = viewModelScope.launch(coroutineExceptionHandler)
    {

        isLoadingState.postValue(LoadingState.LOADING)

        val result = mainRepository.execute(
            MainRepository.Params(
                Constants.ALL_SECTIONS,
                mPeriod,
                Constants.API_KEY
            )
        )
        when (result) {
            is ResultWrapper.Success -> {
                result.value.body()?.let {
                    if (result.value.isSuccessful) {
                        isLoadingState.postValue(LoadingState.LOADED)
                        mostViewedLiveData.postValue(result.value.body());

                    } else {
                        isLoadingState.postValue(LoadingState.LOADED)
                        articleNavigator.onError(result.value.errorBody().toString())


                    }
                }

            }
            is ResultWrapper.GenericError -> {
                isLoadingState.postValue(LoadingState.LOADED)
                articleNavigator.onError("Failed to get response")
            }
            is ResultWrapper.NetworkError -> {
                isLoadingState.postValue(LoadingState.LOADED)
                articleNavigator.onError("Please check internet connection!")
            }
        }
    }

}