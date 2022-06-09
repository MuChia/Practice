package priv.muchia.practice.model

/**
 * FileName: PagingData
 * Author: MuChia
 * Date: 2022/6/8 01:42
 * Description:
 */
data class PagingData<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
