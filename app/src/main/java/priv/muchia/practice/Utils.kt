package priv.muchia.practice

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