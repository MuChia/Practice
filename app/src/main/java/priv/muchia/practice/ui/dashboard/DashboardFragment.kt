package priv.muchia.practice.ui.dashboard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import priv.muchia.practice.adapter.ArticleAdapter
import priv.muchia.practice.adapter.HomeViewPagerAdapter
import priv.muchia.practice.adapter.SitesAdapter
import priv.muchia.practice.databinding.FragmentDashboardBinding
import priv.muchia.practice.ui.home.HomeViewModel

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val articleAdapter = ArticleAdapter()
    private val sitesAdapter = SitesAdapter()
    private val titles = arrayOf("热门博文", "常用网站")
    private val contentAdapter = HomeViewPagerAdapter(titles)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        activity?.window?.statusBarColor = Color.parseColor("#FF03DAC5")

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchTv.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }

        val viewPager = binding.homeContentVp
        viewPager.adapter = contentAdapter
        binding.homeTabLayout.setupWithViewPager(viewPager)

        val articleRecycleView = binding.homeArticleRv
        articleRecycleView.adapter = articleAdapter

        val sitesRecyclerView = binding.homeSitesRv
        sitesRecyclerView.adapter = sitesAdapter

        viewModel.refreshData()
        showSites()

        viewModel.setPage(0)
        showArticles()


        return root
    }

    private fun showSites() {
        viewModel.sites.observe(viewLifecycleOwner) {
            val sites = it.getOrNull()
            if (!sites.isNullOrEmpty()) {
                sitesAdapter.submitList(sites)
            }
        }
    }

    private fun showArticles() {
        viewModel.articles.observe(viewLifecycleOwner) {
            val articles = it.getOrNull()
            if ((null != articles) && articles.datas.isNotEmpty()) {

                articleAdapter.addData(articles.datas)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}