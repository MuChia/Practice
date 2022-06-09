package priv.muchia.newwork_core

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * FileName: BaseNetwork
 * Author: MuChia
 * Date: 2022/6/8 01:19
 * Description:
 */
open class BaseNetwork {
    suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {
            enqueue((object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (null != body) it.resume(body)
                    else it.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }
            }))
        }
    }
}