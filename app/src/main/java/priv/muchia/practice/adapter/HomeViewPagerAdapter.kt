package priv.muchia.practice.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * FileName: HomeViewPagerAdapter
 * Author: MuChia
 * Date: 2022/6/14 00:17
 * Description:
 */
class HomeViewPagerAdapter(private val titles: Array<String>) : PagerAdapter() {
    override fun getCount() = titles.size

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): View =
        container.getChildAt(position)


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeViewAt(position)
    }

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}