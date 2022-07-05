package priv.muchia.practice.ui.article

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import priv.muchia.practice.R

class FAQsFragment : Fragment() {
    companion object {
        fun newInstance() = FAQsFragment()
    }

    private lateinit var viewModel: FAQsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_faqs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FAQsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}