package priv.muchia.newwork_core

import androidx.lifecycle.liveData
import kotlin.coroutines.CoroutineContext

/**
 * FileName: BaseRestory
 * Author: MuChia
 * Date: 2022/6/8 01:20
 * Description:
 */
open class BaseRepository {
    fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
}