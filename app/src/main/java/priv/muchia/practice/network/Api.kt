package priv.muchia.practice.network

import priv.muchia.newwork_core.BaseData
import priv.muchia.practice.model.*
import retrofit2.Call
import retrofit2.http.*

/**
 * FileName: api
 * Author: MuChia
 * Date: 2022/6/8 01:26
 * Description:
 */
interface Api {

    /**
     * 首页轮播
     */
    @GET("banner/json")
    fun getBanner(): Call<BaseData<List<BannerData>>>

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    fun getOfficialAccounts(): Call<BaseData<List<TreeData>>>

    /**
     * 获取公众号历史文章
     * @param page 页码
     * @param id 公众号id
     */
    @GET("wxarticle/list/{id}/{page}/json")
    fun getOfficialArticle(@Path("page") page: Int, @Path("id") id: Int): Call<BaseData<PagingData<ArticleData>>>


    @GET("tree/json")
    fun getTree(): Call<BaseData<List<TreeData>>>

    @GET("article/list/{page}/json?cid={cid}")
    fun getTreeArticles(@Path("page") page: Int, @Path("cid") cid: Int): Call<BaseData<PagingData<ArticleData>>>

    @GET("article/listproject/{page}/json")
    fun getNewProject(@Path("page") page: Int): Call<BaseData<PagingData<ArticleData>>>

    @GET("project/tree/json")
    fun getProjectClassify(): Call<BaseData<List<TreeData>>>

    @GET("project/list/{page}/json?cid={cid}")
    fun getProjects(@Path("page") page: Int, @Path("cid") cid: Int): Call<BaseData<PagingData<ArticleData>>>

    @GET("article/list/{page}/json")
    fun getMainArticles(@Path("page") page: Int): Call<BaseData<PagingData<ArticleData>>>

    @GET("wenda/list/{page}/json")
    fun getFAQs(@Path("page") page: Int): Call<BaseData<PagingData<ArticleData>>>

    @GET("article/list/{page}/json?cid={cid}&order_type=1")
    fun getCourseCatalog(@Path("page") page: Int, @Path("cid") cid: Int): Call<BaseData<PagingData<ArticleData>>>

    @GET("chapter/547/sublist/json")
    fun getCourses(): Call<BaseData<List<TreeData>>>

    @GET("user_article/list/{page}/json")
    fun getSquaresArticles(@Path("page") page: Int): Call<BaseData<PagingData<ArticleData>>>

    @GET("friend/json")
    fun getSites(): Call<BaseData<List<SitesData>>>

    @GET("hotkey/json")
    fun getHotkey(): Call<BaseData<List<HotKeyData>>>

    @GET("navi/json")
    fun getNavigation(): Call<BaseData<List<NavigationData>>>

    @FormUrlEncoded
    @POST("article/query/{page}/json")
    fun search(@Path("page") page: Int, @Field("k") key: String): Call<BaseData<PagingData<SearchResultData>>>
}