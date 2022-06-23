package priv.muchia.practice

import android.content.Intent
import android.os.*
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import priv.muchia.practice.ui.demo.TagLayout

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        startActivity(Intent(this, MainActivity::class.java))
        finish()

//        val handler = Handler(Looper.getMainLooper()){
//            false
//        }
//
//        handler.sendMessage(Message.obtain())

    }
}