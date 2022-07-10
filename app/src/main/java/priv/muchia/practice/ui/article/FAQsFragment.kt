package priv.muchia.practice.ui.article

import androidx.lifecycle.ViewModelProvider
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
import priv.muchia.practice.databinding.FragmentFaqsBinding
import priv.muchia.practice.databinding.FragmentSquaresBinding

class FAQsFragment : Fragment() {
    companion object {
        fun newInstance() = FAQsFragment()
    }

    private var _binding: FragmentFaqsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FAQsViewModel by viewModels()
    private val adapter = ArticleAdapter()
    private var page = 1
    private var curPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFaqsBinding.inflate(inflater, container, false)

        val refreshLayout = binding.faqsRefreshLayout
        refreshLayout.setColorSchemeResources(R.color.teal_200)
        refreshLayout.setOnRefreshListener {
            viewModel.setPage(1)
        }

        val recyclerView = binding.faqsRecyclerView
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

        viewModel.setPage(1)
        showResult()

        return binding.root
    }

    private fun showResult() {
        viewModel.articles.observe(viewLifecycleOwner) {
            binding.faqsRefreshLayout.isRefreshing = false
            val result = it.getOrNull()
            if ((null != result) && result.datas.isNotEmpty()) {
                curPage = result.curPage - 1
                if (curPage == 1) adapter.setData(result.datas)
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