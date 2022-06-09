package priv.muchia.practice.network

import priv.muchia.practice.model.BaseData
import priv.muchia.practice.model.MainArticle
import priv.muchia.practice.model.PagingData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * FileName: api
 * Author: MuChia
 * Date: 2022/6/8 01:26
 * Description:
 */
interface Api {
    @GET("article/list/{page}/json")
    fun getMainArticles(@Path("page") page: Int): Call<BaseData<PagingData<MainArticle>>>
}