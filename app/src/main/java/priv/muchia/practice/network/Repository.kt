package priv.muchia.practice.network

import kotlinx.coroutines.Dispatchers
import priv.muchia.newwork_core.BaseRepository

/**
 * FileName: Repository
 * Author: MuChia
 * Date: 2022/6/8 01:34
 * Description:
 */
object Repository: BaseRepository() {

    fun getMainArticles(page: Int) = fire(Dispatchers.IO) {
        val articlesResponse = Network.mainArticles(page)
        if (articlesResponse.errorCode == 200) {
            val pagingData = articlesResponse.data
            Result.success(pagingData)
        } else {
            Result.failure(Throwable(articlesResponse.errorMsg))
        }
    }
}