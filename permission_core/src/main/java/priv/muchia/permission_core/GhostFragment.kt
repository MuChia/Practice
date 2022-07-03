package priv.muchia.permission_core

import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

typealias PermissionCallback = (Boolean, List<String>) -> Unit

class GhostFragment : Fragment() {

    private var callback: PermissionCallback? = null

    private val permissionsResultContract = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) { map ->

        val deniedList = ArrayList<String>()
        map.forEach {
            if (!it.value) deniedList.add(it.key)
            Log.d("Permission", "${it.key} is ${it.value}")
        }
        val allGranted = deniedList.isEmpty()
        callback?.let { it(allGranted, deniedList) }
    }

    fun requestNow(callback: PermissionCallback, vararg permissions: String) {
        this.callback = callback
        permissionsResultContract.launch(permissions)
    }
}
