package priv.muchia.practice.ui.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import priv.muchia.practice.network.Repository

class NavigationViewModel : ViewModel() {
    private val refreshData by lazy { MutableLiveData<Any?>() }
    private val _naviData = refreshData.switchMap {
        Repository.getNavigation()
    }

    val naviData = _naviData

    fun refreshData(){
        refreshData.value = refreshData.value
    }
}