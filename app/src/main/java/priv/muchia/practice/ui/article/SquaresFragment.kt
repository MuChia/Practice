package priv.muchia.practice.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import priv.muchia.practice.R

class SquaresFragment : Fragment() {
    companion object {
        fun newInstance() = SquaresFragment()
    }

    private lateinit var viewModel: SquaresViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_squares, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SquaresViewModel::class.java)
        // TODO: Use the ViewModel
    }
}