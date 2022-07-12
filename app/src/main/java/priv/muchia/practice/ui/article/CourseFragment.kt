package priv.muchia.practice.ui.article

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import priv.muchia.practice.adapter.CourseAdapter
import priv.muchia.practice.databinding.FragmentCourseBinding

class CourseFragment : Fragment() {
    companion object {
        fun newInstance() = CourseFragment()
    }

    private val viewModel: CourseViewModel by viewModels()
    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!
    private val adapter = CourseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =  FragmentCourseBinding.inflate(inflater, container, false)
        val viewPager = binding.courseRv
        viewPager.layoutManager = LinearLayoutManager(context)
        adapter.setOnItemClickListener { _, position ->
            val course = viewModel.courseData.value?.getOrNull()?.get(position)
            val intent = Intent(activity, CourseCatalogActivity::class.java)
            course?.let {
                intent.putExtra("id", it.id)
                intent.putExtra("title", it.name)
                startActivity(intent)
            }

        }
        viewPager.adapter = adapter

        viewModel.refresh()
        showResult()

        return binding.root
    }

    private fun showResult() {
        viewModel.courseData.observe(viewLifecycleOwner) {
            val result = it.getOrNull()
            if (!result.isNullOrEmpty()) {
                adapter.setData(result)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}