package priv.muchia.practice.network

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

    fun getMainArticles(page: Int) = fire(Dispatchers.IO) {
        handleResponse(Network.getMainArticles(page))
    }



}