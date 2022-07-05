package priv.muchia.practice.ui.article

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import priv.muchia.practice.R

class OfficialAccountFragment : Fragment() {
    companion object {
        fun newInstance() = OfficialAccountFragment()
    }

    private lateinit var viewModel: OfficialAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_official_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OfficialAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}