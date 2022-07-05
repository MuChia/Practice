package priv.muchia.practice.ui.article

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import priv.muchia.practice.adapter.ArticleAdapter
import priv.muchia.practice.adapter.ArticleViewPagerAdapter
import priv.muchia.practice.adapter.HomeViewPagerAdapter
import priv.muchia.practice.adapter.SitesAdapter
import priv.muchia.practice.databinding.FragmentArticleBinding
import priv.muchia.practice.ui.home.HomeViewModel

class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val titles = arrayOf("广场", "导航", "教程", "问答", "体系", "项目", "公众号")
    private val fragments = arrayOf(SquaresFragment(),
        NavigationFragment(),
        CourseFragment(),
        FAQsFragment(),
        TreeFragment(),
        ProjectFragment(),
        OfficialAccountFragment())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val articleViewModel =
            ViewModelProvider(this)[ArticleViewModel::class.java]

        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val viewPager = binding.articleViewPager
        viewPager.adapter = ArticleViewPagerAdapter(this, fragments)
        val tabLayout = binding.articleTabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

        binding.articleSearchTv.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}