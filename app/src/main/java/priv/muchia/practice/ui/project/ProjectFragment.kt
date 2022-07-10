package priv.muchia.practice.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import priv.muchia.practice.adapter.ArticleViewPagerAdapter
import priv.muchia.practice.databinding.FragmentProjectBinding
import priv.muchia.practice.ui.article.*

class ProjectFragment : Fragment() {
    private var _binding: FragmentProjectBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val titles = arrayOf("热门项目", "项目分类")
    private val fragments = arrayOf(SquaresFragment(),
        NavigationFragment(),
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val personViewModel =
            ViewModelProvider(this)[ProjectViewModel::class.java]

        _binding = FragmentProjectBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val viewPager = binding.projectViewPager
        viewPager.adapter = ArticleViewPagerAdapter(this, fragments)
        val tabLayout = binding.projectTabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}