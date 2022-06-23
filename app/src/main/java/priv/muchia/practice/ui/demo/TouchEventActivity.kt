package priv.muchia.practice.ui.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import priv.muchia.practice.R
import priv.muchia.practice.action

class TouchEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.d("TouchEvent", "Activity-----DispatchTouchEvent--------${ev.action}------------")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("TouchEvent", "Activity-----TouchEvent-----------${event.action}---------")
        return super.onTouchEvent(event)
//        return true
    }
}