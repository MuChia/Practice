package priv.muchia.practice.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import priv.muchia.practice.R
import priv.muchia.practice.adapter.ArticleAdapter
import priv.muchia.practice.adapter.BannerAdapter
import priv.muchia.practice.adapter.HomeViewPagerAdapter
import priv.muchia.practice.adapter.SitesAdapter
import priv.muchia.practice.databinding.FragmentHomeBinding
import priv.muchia.practice.model.ArticleData
import priv.muchia.practice.model.BannerData
import priv.muchia.practice.ui.custom.CustomItemDecoration

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
    private lateinit var bannerPager: ViewPager2
    private val bannerList = mutableListOf<BannerData>()
    private var index = 1
    private val articleList = mutableListOf<ArticleData>()
    private var page = 0
    private var curPage = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        bannerPager = binding.homeBannerVp
        bannerPager.adapter = bannerAdapter

        bannerPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                when (position) {
                    0 -> {
                        index = 3
                        bannerPager.setCurrentItem(index, false)
                    }
                    4 -> {
                        index = 1
                        bannerPager.setCurrentItem(index, false)
                    }
                }
            }


        })
        val viewPager = binding.homeContentVp
        viewPager.adapter = contentAdapter
        binding.homeTabLayout.setupWithViewPager(viewPager)

        val articleRecycleView = binding.homeArticleRv
        articleRecycleView.adapter = articleAdapter
        articleAdapter.setOnItemClickListener { view, position ->
            when(view.id){
                R.id.home_collect_img -> {
                    articleAdapter.getData()[position].collect = true
                    articleAdapter.notifyItemChanged(position, "collect")
                }
            }
        }
        articleRecycleView.addItemDecoration(CustomItemDecoration())
        articleRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((recyclerView.layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition() >= articleAdapter.itemCount - 2 && dy > 0 && page == curPage
                ) {
                    viewModel.setPage(++page)
                }
            }
        })
        val sitesRecyclerView = binding.homeSitesRv
        sitesRecyclerView.adapter = sitesAdapter


        showBanner()
        showSites()

        showArticles()
        slideshow()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.refreshData()


        viewModel.setPage(0)

    }

    private fun slideshow() {
        lifecycleScope.launchWhenResumed {
            launch(Dispatchers.IO) {
                while (true) {
                    delay(5000)
                    withContext(Dispatchers.Main) {
                        bannerPager.currentItem = index++
                    }
                }
            }
        }
    }

    private fun showBanner() {
        viewModel.banners.observe(viewLifecycleOwner) {
            val banners = it.getOrNull()
            if (!banners.isNullOrEmpty()) {
                bannerList.add(banners[banners.size - 1])
                bannerList.addAll(banners)
                bannerList.add(banners[0])
                bannerAdapter.setData(bannerList)
            }
        }
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
                curPage = articles.curPage - 1
                if (curPage == 0) articleAdapter.setData(articles.datas)
                else articleAdapter.addData(articles.datas)
            }
            page = curPage
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}