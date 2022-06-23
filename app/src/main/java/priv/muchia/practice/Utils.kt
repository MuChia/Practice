package priv.muchia.practice

import android.content.res.Resources
import android.util.TypedValue
import android.view.MotionEvent
import android.widget.Toast

/**
 * FileName: Utils
 * Author: MuChia
 * Date: 2022/6/8 02:17
 * Description:
 */
fun String.toast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}

val MotionEvent.action: String
    get() = when (this.actionMasked) {
        MotionEvent.ACTION_DOWN -> "ACTION_DOWN"
        MotionEvent.ACTION_UP -> "ACTION_UP"
        MotionEvent.ACTION_MOVE -> "ACTION_MOVE"
        MotionEvent.ACTION_CANCEL -> "ACTION_CANCEL"
        else -> "other"
    }
val Float.dp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics)
val Int.dp: Float
    get() = this.toFloat().dp