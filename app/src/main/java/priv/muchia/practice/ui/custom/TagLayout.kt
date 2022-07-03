package priv.muchia.practice.ui.custom

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.*
import priv.muchia.practice.R
import kotlin.math.max

/**
 * FileName: TagLayout
 * Author: MuChia
 * Date: 2022/6/22 11:19
 * Description:
 */
class TagLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
    private val childrenBounds = mutableListOf<Rect>()
    private var clickListener: ((Int) -> Unit)? = null

    fun setTitles(titles: List<String>){
        for ((index, title) in titles.withIndex()) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_tag_layout, this, false)
            val textView = view.findViewById<TextView>(R.id.tag_title_tv)
            textView.text = title
            clickListener?.let { listener ->
                view.setOnClickListener {
                    listener(index)
                }
            }
            addView(view)
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        clickListener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var widthUsed = 0
        var heightUsed = 0
        var maxLineHeight = 0
        var selfWidth = 0
        var selfHeight = 0
        val widthMeasureSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec)

        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(child,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                heightUsed)

            if (widthMeasureMode != MeasureSpec.UNSPECIFIED &&
                widthUsed + child.measuredWidth + child.marginStart
                + child.marginRight >= widthMeasureSize
            ) {
                widthUsed = 0
                heightUsed += maxLineHeight
                maxLineHeight = 0

                measureChildWithMargins(child,
                    widthMeasureSpec,
                    0,
                    heightMeasureSpec,
                    heightUsed)
            }

            if (index >= childrenBounds.size) childrenBounds.add(Rect())
            val childBounds = childrenBounds[index]
            childBounds.set(widthUsed + child.marginStart,
                heightUsed + child.marginTop,
                widthUsed + child.measuredWidth + child.marginStart,
                heightUsed + child.measuredHeight + child.marginTop)

            widthUsed += child.measuredWidth + child.marginStart + child.marginRight
            maxLineHeight =
                max(maxLineHeight, child.measuredHeight + child.marginTop + child.marginBottom)

            selfWidth = max(selfWidth, widthUsed)
            selfHeight = heightUsed + maxLineHeight
        }

        setMeasuredDimension(selfWidth, selfHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val bounds = childrenBounds[index]
            child.layout(bounds.left, bounds.top, bounds.right, bounds.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

}