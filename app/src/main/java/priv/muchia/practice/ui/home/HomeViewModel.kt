package priv.muchia.practice.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import priv.muchia.practice.network.Repository

class HomeViewModel : ViewModel() {
    private val refreshData: MutableLiveData<Any?> by lazy { MutableLiveData<Any?>() }
    private val pageData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    private val _banners = refreshData.switchMap {
        Repository.getBanner()
    }
    val banners = _banners

    private val _articles = pageData.switchMap {
        Repository.getMainArticles(it)
    }
    val articles = _articles

    private val _sites = refreshData.switchMap {
        Repository.getSites()
    }
    val sites = _sites

    fun refreshData() {
        refreshData.value = refreshData.value
    }

    fun setPage(page: Int){
        pageData.value = page
    }
}