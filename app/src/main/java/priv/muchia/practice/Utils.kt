package priv.muchia.practice

import android.view.MotionEvent
import android.widget.Toast

/**
 * FileName: Utils
 * Author: MuChia
 * Date: 2022/6/8 02:17
 * Description:
 */
fun String.toast(duration : Int = Toast.LENGTH_SHORT){
    Toast.makeText(MyApplication.context, this, duration).show()
}

fun MotionEvent.action() : String{
    return when(this.actionMasked){
        MotionEvent.ACTION_DOWN -> "ACTION_DOWN"
        MotionEvent.ACTION_UP -> "ACTION_UP"
        MotionEvent.ACTION_MOVE -> "ACTION_MOVE"
        MotionEvent.ACTION_CANCEL -> "ACTION_CANCEL"
        else -> "other"
    }
}