package priv.muchia.practice.ui.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import priv.muchia.practice.network.Repository

class CourseViewModel : ViewModel() {

    private val refreshData: MutableLiveData<Any?> by lazy { MutableLiveData<Any?>() }
    private val _courseData = refreshData.switchMap {
        Repository.getCourses()
    }
    val courseData = _courseData

    fun refresh() {
        refreshData.value = refreshData.value
    }
}