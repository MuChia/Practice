package priv.muchia.practice.ui.custom

import android.graphics.*
import android.graphics.drawable.Drawable
import android.print.PrintAttributes
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import priv.muchia.practice.R
import priv.muchia.practice.dp
import kotlin.math.roundToInt

/**
 * FileName: CustomItemDecoration
 * Author: MuChia
 * Date: 2022/7/2 22:21
 * Description:
 */
class CustomItemDecoration : RecyclerView.ItemDecoration() {
    private val mBounds = Rect();
    private val dividerHeight = 1.dp
    var mDivider: Drawable? = null
    var margins = PrintAttributes.Margins(10.dp.toInt(), 0, 10.dp.roundToInt(), 0)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        for (child in parent.children) {
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val left = margins.leftMils
            val right = parent.width - margins.rightMils
            val top = mBounds.bottom
            mDivider?.let {
                val bottom = mBounds.bottom - it.intrinsicHeight
                it.setBounds(left, top, right, bottom)
                it.draw(c)
            } ?: run {
                val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = Color.parseColor("#FF448888")
                    strokeWidth = dividerHeight
                    style = Paint.Style.FILL_AND_STROKE
                }
                val bottom = mBounds.bottom - dividerHeight
                val dashPathEffect = DashPathEffect(floatArrayOf(10.dp, 10.dp),0f)
                paint.pathEffect = dashPathEffect
                c.drawLine(left.toFloat(), top.toFloat(), right.toFloat(), bottom, paint)
            }
        }

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val bottom = mDivider?.intrinsicHeight?: dividerHeight.toInt()
        outRect.set(0, 0, 0, bottom)
    }


}