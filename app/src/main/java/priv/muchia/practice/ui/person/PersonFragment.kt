package priv.muchia.practice.ui.person

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import priv.muchia.permission_core.Permission
import priv.muchia.practice.databinding.FragmentPersonBinding
import priv.muchia.practice.toast
import priv.muchia.practice.ui.demo.DemoActivity
import priv.muchia.practice.ui.project.ProjectViewModel

class PersonFragment : Fragment() {
    private var _binding: FragmentPersonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val personViewModel =
            ViewModelProvider(this).get(ProjectViewModel::class.java)

        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.textPracticeDemo.setOnClickListener {
            startActivity(Intent(activity, DemoActivity::class.java))
        }

        binding.userNameTv.setOnClickListener {

            Permission.request(activity!!, Manifest.permission.CALL_PHONE) { granted, _ ->
                if (granted) {
                    val intent = Intent(Intent.ACTION_DIAL)
//                    intent.data = Uri.parse("tel:10086")
                    startActivity(intent)
                } else {

                    if (activity?.checkSelfPermission(Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_DENIED
                    ) {
                        shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE).toString().toast()
                    }
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}