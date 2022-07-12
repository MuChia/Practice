package priv.muchia.practice.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import priv.muchia.practice.MyApplication.Companion.context
import priv.muchia.practice.R
import priv.muchia.practice.adapter.ArticleAdapter
import priv.muchia.practice.adapter.CourseCatalogAdapter
import priv.muchia.practice.databinding.ActivityCourseCatalogBinding

class CourseCatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseCatalogBinding

    private val viewModel: CourseCatalogViewModel by viewModels()
    private val adapter = CourseCatalogAdapter()
    private var page = 0
    private var curPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.cid = intent.getIntExtra("id", 0)
        binding.layoutActionBar.titleTv.text = intent.getStringExtra("title")

        val refreshLayout = binding.courseCatalogRefresh
        refreshLayout.setColorSchemeResources(R.color.teal_200)
        refreshLayout.setOnRefreshListener {
            viewModel.setPage(0)
        }

        val recyclerView = binding.courseCatalogRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((recyclerView.layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition() >= adapter.itemCount - 2
                    && dy > 0 && page == curPage
                ) {
                    viewModel.setPage(++page)
                }
            }
        })

        viewModel.setPage(0)
        showResult()

    }

    private fun showResult() {
        viewModel.catalogData.observe(this) {
            binding.courseCatalogRefresh.isRefreshing = false
            val result = it.getOrNull()
            if ((null != result) && result.datas.isNotEmpty()) {
                curPage = result.curPage - 1
                if (curPage == 0) adapter.setData(result.datas)
                else adapter.addData(result.datas)
            }
            page = curPage
            page = curPage
        }
    }

}