package priv.muchia.practice.ui.demo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import priv.muchia.practice.action

/**
 * FileName: TouchEventViewGroup
 * Author: MuChia
 * Date: 2022/6/15 14:24
 * Description:
 */
class TouchEventView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Log.e("TouchEvent", "View-----DispatchTouchEvent----------${event.action()}----------")
        return super.dispatchTouchEvent(event)
//        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e("TouchEvent", "View-----TouchEvent---------${event.action()}-----------")
        return super.onTouchEvent(event)
//        return true
    }

}