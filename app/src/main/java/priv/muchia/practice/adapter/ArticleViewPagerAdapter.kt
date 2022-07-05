package priv.muchia.practice.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * FileName: HomeViewPagerAdapter
 * Author: MuChia
 * Date: 2022/6/14 00:17
 * Description:
 */
class ArticleViewPagerAdapter(val fragment: Fragment, private val fragments: Array<Fragment>) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}