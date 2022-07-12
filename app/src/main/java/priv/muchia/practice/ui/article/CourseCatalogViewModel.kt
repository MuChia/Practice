package priv.muchia.practice.ui.article

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import priv.muchia.practice.network.Repository

class CourseCatalogViewModel : ViewModel() {

    var cid = 0

    private val pageData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    private val _catalogData = pageData.switchMap {
        Repository.getCourseCatalog(it, cid)
//        Repository.getMainArticles(it)
    }
    val catalogData = _catalogData

    fun setPage(page: Int) {
        pageData.value = page
        Log.d("setPage", "page:$page, cid: $cid")
    }
}