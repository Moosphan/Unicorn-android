package com.moos.unicorn.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.moos.unicorn.base.BaseViewModel
import com.moos.unicorn.model.data.UnicornRankList
import com.moos.unicorn.model.repository.RankRepository
import com.moos.unicorn.net.ExceptionHandler
import kotlinx.coroutines.launch

/**
 * viewModel of rank page.
 */
class RankViewModel : BaseViewModel() {

    private val repository: RankRepository by lazy { RankRepository() }

    val mRankData: MutableLiveData<UnicornRankList> = MutableLiveData()
    val mError: MutableLiveData<String> = MutableLiveData()

    fun fetchRankData() {
        viewModelScope.launch {
            val result = repository.requestRankData()
            Log.e("RankViewModel", "返回的独角兽数据：${Gson().toJson(result)}")
            try {
                mRankData.postValue(result)
            }catch (e: Exception) {
                mError.postValue(ExceptionHandler.errorMessage)
            }

        }
    }
}