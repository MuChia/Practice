package priv.muchia.permission_core

import androidx.fragment.app.FragmentActivity

/**
 * FileName: Permission
 * Author: MuChia
 * Date: 2022/6/24 21:18
 * Description:
 */
object Permission {

    private const val TAG = "GhostFragment"

    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val tagFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (tagFragment != null) {
            tagFragment as GhostFragment
        } else {
            val ghostFragment = GhostFragment()
            fragmentManager.beginTransaction()
                .add(ghostFragment, TAG)
                .commitNow()
            ghostFragment
        }
        fragment.requestNow(callback, *permissions)
    }
}