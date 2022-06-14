package priv.muchia.practice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import priv.muchia.practice.adapter.ArticleAdapter
import priv.muchia.practice.adapter.BannerAdapter
import priv.muchia.practice.adapter.HomeViewPagerAdapter
import priv.muchia.practice.adapter.SitesAdapter
import priv.muchia.practice.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val bannerAdapter = BannerAdapter()
    private val articleAdapter = ArticleAdapter()
    private val sitesAdapter = SitesAdapter()
    private val titles = arrayOf("热门博文", "常用网站")
    private val contentAdapter = HomeViewPagerAdapter(titles)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val bannerPager = binding.homeBannerVp
        bannerPager.adapter = bannerAdapter

        val viewPager= binding.homeContentVp
        viewPager.adapter = contentAdapter
        binding.homeTabLayout.setupWithViewPager(viewPager)

        val articleRecycleView = binding.homeArticleRv
        articleRecycleView.adapter = articleAdapter

        val sitesRecyclerView = binding.homeSitesRv
        sitesRecyclerView.adapter = sitesAdapter

        viewModel.refreshData()
        refreshBanner()
        refreshSites()

        viewModel.setPage(0)
        refreshArticle()

        return binding.root
    }

    private fun refreshBanner() {
        viewModel.banners.observe(viewLifecycleOwner) {
            val banners = it.getOrNull()
            if (!banners.isNullOrEmpty()) {
                bannerAdapter.setData(banners)
            }
        }
    }

    private fun refreshSites() {
        viewModel.sites.observe(viewLifecycleOwner) {
            val sites = it.getOrNull()
            if (!sites.isNullOrEmpty()) {
                sitesAdapter.submitList(sites)
            }
        }
    }

    private fun refreshArticle() {
        viewModel.articles.observe(viewLifecycleOwner) {
            val articles = it.getOrNull()
            if ((null != articles) && articles.datas.isNotEmpty()) {
                articleAdapter.submitList(articles.datas)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}