package priv.muchia.practice.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import priv.muchia.newwork_core.BaseData
import priv.muchia.newwork_core.BaseRepository
import priv.muchia.practice.toast

/**
 * FileName: Repository
 * Author: MuChia
 * Date: 2022/6/8 01:34
 * Description:
 */
object Repository : BaseRepository() {

    private fun <T> handleResponse(resp: BaseData<T>) =
        if (resp.errorCode == 0) {
            Result.success(resp.data)
        } else {
            resp.errorMsg.toast()
            Result.failure(Throwable(resp.errorMsg))
        }

    fun getBanner() = fire(Dispatchers.IO) {
        handleResponse(Network.getBanner())
    }

    fun getProjectClassify() = fire(Dispatchers.IO) {
        handleResponse(Network.getProjectClassify())
    }

    fun getTree() = fire(Dispatchers.IO) {
        handleResponse(Network.getTree())
    }

    fun getCourses() = fire(Dispatchers.IO) {
        handleResponse(Network.getCourses())
    }

    fun getCourseCatalog(page: Int, cid: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getCourseCatalog(page, cid))
    }

    fun getTreeArticles(page: Int, cid: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getTreeArticles(page, cid))
    }

    fun getProjects(page: Int, cid: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getProjects(page, cid))
    }

    fun getOfficialAccounts() = fire(Dispatchers.IO) {
        handleResponse(Network.getOfficialAccounts())
    }

    fun getOfficialArticle(page: Int, id: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getOfficialArticle(page, id))
    }

    fun getFAQs(page: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getFAQs(page))
    }

    fun getSites() = fire(Dispatchers.IO) {
        handleResponse(Network.getSites())
    }

    fun getHotKey() = fire(Dispatchers.IO) {
        handleResponse(Network.getHotKey())
    }
    fun getNavigation() = fire(Dispatchers.IO) {
        handleResponse(Network.getNavigation())
    }

    fun getMainArticles(page: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getMainArticles(page))
    }

    fun getNewProject(page: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getNewProject(page))
    }

    fun getSquaresArticles(page: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getSquaresArticles(page))
    }

    fun search(page: Int, key: String) = fire(Dispatchers.IO) {
        handleResponse(Network.search(page, key))
    }



}