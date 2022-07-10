package priv.muchia.practice.ui.article

import androidx.lifecycle.*
import priv.muchia.practice.model.ArticleData
import priv.muchia.practice.model.PagingData
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
    private val hotKeyData: MutableLiveData<Any?> by lazy { MutableLiveData<Any?>() }
    private val _refresh = pageData.switchMap {
        Repository.search(it, keyData.value ?: "")
    }
    private val _search = keyData.switchMap {
        Repository.search(pageData.value ?: 0, it)
    }
    private val _hotKey = hotKeyData.switchMap {
        Repository.getHotKey()
    }.map { result ->
        result.map { list ->
            list.map { hotkey ->
                hotkey.name
            }
        }
    }

    val hotkey = _hotKey

    private val _resultData: MediatorLiveData<Result<PagingData<ArticleData>>> by lazy {
        MediatorLiveData<Result<PagingData<ArticleData>>>()
            .apply {
                addSource(_refresh) {
                    value = it
                }
                addSource(_search) {
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

    fun refreshHotKey(){
        hotKeyData.value = hotKeyData.value
    }
}