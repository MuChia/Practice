package priv.muchia.practice.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import priv.muchia.practice.R
import priv.muchia.practice.adapter.ArticleAdapter
import priv.muchia.practice.databinding.FragmentSquaresBinding

class SquaresFragment : Fragment() {
    companion object {
        fun newInstance() = SquaresFragment()
    }

    private var _binding: FragmentSquaresBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SquaresViewModel by viewModels()
    private val adapter = ArticleAdapter()
    private var page = 0
    private var curPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSquaresBinding.inflate(inflater, container, false)

        val refreshLayout = binding.squareRefreshLayout
        refreshLayout.setColorSchemeResources(R.color.teal_200)
        refreshLayout.setOnRefreshListener {
            viewModel.setPage(0)
        }

        val recyclerView = binding.squareRecyclerView
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

        return binding.root
    }

    private fun showResult() {
        viewModel.articles.observe(viewLifecycleOwner) {
            binding.squareRefreshLayout.isRefreshing = false
            val result = it.getOrNull()
            if ((null != result) && result.datas.isNotEmpty()) {
                curPage = result.curPage - 1
                if (curPage == 0) adapter.setData(result.datas)
                else adapter.addData(result.datas)
            }
            page = curPage
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}