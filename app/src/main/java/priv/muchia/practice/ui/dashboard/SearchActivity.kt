package priv.muchia.practice.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import priv.muchia.practice.R
import priv.muchia.practice.adapter.SearchAdapter
import priv.muchia.practice.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val adapter = SearchAdapter()
    private var keyWord = ""
    private var page = 0
    private var curPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val refreshLayout = binding.searchRefresh
        refreshLayout.setColorSchemeResources(R.color.teal_200)
        refreshLayout.setOnRefreshListener {
            viewModel.setPage(0)
        }

        val recyclerView = binding.searchRv
        recyclerView.layoutManager = LinearLayoutManager(this)
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

        val editText = binding.searchEt
        editText.setOnEditorActionListener { v, _, _ ->
            refreshLayout.isRefreshing = true
            keyWord = v.text.toString()
            viewModel.search(keyWord)
            true
        }

        showResult()
    }

    private fun showResult() {
        viewModel.resultData.observe(this) {
            binding.searchRefresh.isRefreshing = false
            val result = it.getOrNull()
            if ((null != result) && result.datas.isNotEmpty()) {
                curPage = result.curPage - 1
                if (curPage == 0) adapter.setData(result.datas)
                else adapter.addData(result.datas)
            }
            page = curPage
        }
    }
}