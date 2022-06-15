package priv.muchia.practice.ui.demo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.children
import priv.muchia.practice.action

/**
 * FileName: TouchEventViewGroup
 * Author: MuChia
 * Date: 2022/6/15 14:24
 * Description:
 */
class TouchEventViewGroup(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.w("TouchEvent", "ViewGroup-----InterceptTouchEvent----------${ev.action()}----------")
        return super.onInterceptTouchEvent(ev)
//        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.w("TouchEvent", "ViewGroup-----TouchEvent----------${event.action()}----------")
        return super.onTouchEvent(event)
//        return true
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        children.forEach {
            it.layout(l + 100, t + 100, r - 100, b - 100)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.w("TouchEvent", "ViewGroup-----DispatchTouchEvent---------${ev.action()}-----------")
        return super.dispatchTouchEvent(ev)
//        return true
    }

}