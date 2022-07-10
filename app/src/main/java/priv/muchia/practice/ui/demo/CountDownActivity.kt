package priv.muchia.practice.ui.demo

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.Button
import android.widget.PopupWindow
import priv.muchia.practice.R
import priv.muchia.practice.databinding.ActivityCountDownBinding
import priv.muchia.practice.dp
import priv.muchia.practice.toast
import kotlin.math.abs

class CountDownActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountDownBinding
    private var layoutParams: WindowManager.LayoutParams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val view = layoutInflater.inflate(R.layout.popup_count_down, null)
        val popupWindow = PopupWindow(view, 300.dp.toInt(), 220.dp.toInt()).apply {
            elevation = 10f
            isFocusable = true
            isOutsideTouchable = false
            animationStyle = androidx.appcompat.R.style.Animation_AppCompat_Dialog
        }
        view.findViewById<Button>(R.id.popup_add_btn).setOnClickListener {
            popupWindow.dismiss()
        }

        binding.addTaskBtn.setOnClickListener {
//            popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
            showFloatingWindow()
        }
    }

    private fun showFloatingWindow() {
        layoutParams = WindowManager.LayoutParams()
        layoutParams?.let { layoutParams ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
            }
            layoutParams.format = PixelFormat.RGBA_8888
            layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            //宽高自适应
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            //显示的位置
            layoutParams.x = 300
            layoutParams.y = 300
        }
        // 新建悬浮窗控件
        val view = LayoutInflater.from(this).inflate(R.layout.float_window, null)
        var x = 0f
        var y = 0f
        var _x = 0f
        var _y = 0f

        view.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    x = event.rawX
                    y = event.rawY
                    _x = x
                    _y = y
                }
                MotionEvent.ACTION_MOVE -> {
                    val nowX = event.rawX
                    val nowY = event.rawY
                    val movedX = nowX - x
                    val movedY = nowY - y
                    x = nowX
                    y = nowY
                    layoutParams?.let { layoutParams ->
                        layoutParams.x = layoutParams.x + movedX.toInt()
                        layoutParams.y = layoutParams.y + movedY.toInt()
                        // 更新悬浮窗控件布局
                        windowManager.updateViewLayout(v, layoutParams)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (abs(_x - event.rawX) < 100 && abs(_y - event.rawY) < 100) {
                        v.performClick()
                    }
                }
            }
            false
        }

        view.setOnClickListener {
            "点击悬浮球".toast()
            startActivity(Intent(this, CountDownActivity::class.java))
        }

        // 将悬浮窗控件添加到WindowManager
        windowManager.addView(view, layoutParams)

    }

}