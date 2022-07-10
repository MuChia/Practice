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
    suspend fun getFAQs(page: Int) = mainArticles.getFAQs(page).await()
    suspend fun getNewProject(page: Int) = mainArticles.getNewProject(page).await()
    suspend fun getCourseCatalog(page: Int, cid: Int) = mainArticles.getCourseCatalog(page, cid).await()
    suspend fun getTreeArticles(page: Int, cid: Int) = mainArticles.getTreeArticles(page, cid).await()
    suspend fun getProjects(page: Int, cid: Int) = mainArticles.getProjects(page, cid).await()
    suspend fun getCourses() = mainArticles.getCourses().await()
    suspend fun getOfficialArticle(page: Int, id: Int) = mainArticles.getOfficialArticle(page, id).await()
    suspend fun getOfficialAccounts() = mainArticles.getOfficialAccounts().await()
    suspend fun getSquaresArticles(page: Int) = mainArticles.getSquaresArticles(page).await()
    suspend fun getBanner() = mainArticles.getBanner().await()
    suspend fun getProjectClassify() = mainArticles.getProjectClassify().await()
    suspend fun getTree() = mainArticles.getTree().await()
    suspend fun getSites() = mainArticles.getSites().await()
    suspend fun getHotKey() = mainArticles.getHotkey().await()
    suspend fun getNavigation() = mainArticles.getNavigation().await()
    suspend fun search(page: Int, key: String) = mainArticles.search(page, key).await()
}