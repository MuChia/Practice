package priv.muchia.practice.network

import priv.muchia.newwork_core.BaseNetwork
import priv.muchia.newwork_core.ServiceCreator

/**
 * FileName: Network
 * Author: MuChia
 * Date: 2022/6/8 01:27
 * Description:
 */
object Network: BaseNetwork() {
    private val mainArticles = ServiceCreator.create<Api>()

    suspend fun getMainArticles(page: Int) = mainArticles.getMainArticles(page).await()
    suspend fun getBanner() = mainArticles.getBanner().await()
}