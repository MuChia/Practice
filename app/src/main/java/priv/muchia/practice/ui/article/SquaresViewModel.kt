package priv.muchia.practice.ui.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import priv.muchia.practice.network.Repository

class SquaresViewModel : ViewModel() {

    private val pageData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    private val _articles = pageData.switchMap {
        Repository.getSquaresArticles(it)
    }
    val articles = _articles

    fun setPage(page: Int){
        pageData.value = page
    }
}