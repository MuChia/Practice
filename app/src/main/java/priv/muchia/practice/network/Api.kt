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

    @GET("banner/json")
    fun getBanner(): Call<BaseData<List<BannerData>>>

    @GET("article/list/{page}/json")
    fun getMainArticles(@Path("page") page: Int): Call<BaseData<PagingData<ArticleData>>>

    @GET("friend/json")
    fun getSites(): Call<BaseData<List<SitesData>>>

    @FormUrlEncoded
    @POST("article/query/{page}/json")
    fun search(@Path("page") page: Int, @Field("k") key: String): Call<BaseData<PagingData<SearchResultData>>>
}