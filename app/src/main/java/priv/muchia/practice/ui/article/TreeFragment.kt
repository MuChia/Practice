package priv.muchia.practice.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import priv.muchia.practice.databinding.FragmentTreeBinding

class TreeFragment : Fragment() {
    companion object {
        fun newInstance() = TreeFragment()
    }

    private val viewModel: TreeViewModel by viewModels()
    private var _binding: FragmentTreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =  FragmentTreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}