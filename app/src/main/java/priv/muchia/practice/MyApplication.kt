package priv.muchia.practice

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * FileName: MyApplication
 * Author: MuChia
 * Date: 2022/6/8 02:18
 * Description:
 */
class MyApplication: Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}