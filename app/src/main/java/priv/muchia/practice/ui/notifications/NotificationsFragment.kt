package priv.muchia.practice.ui.notifications

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import priv.muchia.permission_core.Permission
import priv.muchia.permission_core.PermissionCallback
import priv.muchia.practice.databinding.FragmentNotificationsBinding
import priv.muchia.practice.toast
import priv.muchia.practice.ui.demo.DemoActivity

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
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