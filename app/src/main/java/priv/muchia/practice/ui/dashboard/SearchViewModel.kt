package priv.muchia.practice.ui.dashboard

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import priv.muchia.practice.model.PagingData
import priv.muchia.practice.model.SearchResultData
import priv.muchia.practice.network.Repository

/**
 * FileName: SearchViewModel
 * Author: MuChia
 * Date: 2022/6/18 00:15
 * Description:
 */
class SearchViewModel : ViewModel() {
    private val pageData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    private val keyData: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private val _refresh = pageData.switchMap {
        Repository.search(it, keyData.value ?: "")
    }

    private val _search = keyData.switchMap {
        Repository.search(pageData.value ?: 0, it)
    }

    private val _resultData: MediatorLiveData<Result<PagingData<SearchResultData>>> by lazy {
        MediatorLiveData<Result<PagingData<SearchResultData>>>()
            .apply {
                addSource(_refresh) {
                    value = it
                }
                addSource(_search){
                    value = it
                }
            }
    }

    val resultData = _resultData

    fun search(key: String) {
        keyData.value = key
    }

    fun setPage(page: Int) {
        pageData.value = page
    }
}