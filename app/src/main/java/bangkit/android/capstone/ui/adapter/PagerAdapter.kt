package bangkit.android.capstone.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import bangkit.android.capstone.R
import bangkit.android.capstone.ui.detaill.ProductActivity
import bangkit.android.capstone.ui.fragment.FacewashFragment
import bangkit.android.capstone.ui.fragment.SerumFragment
import bangkit.android.capstone.ui.fragment.TonerFragment

class PagerAdapter(private val mContext: Context, fm: FragmentManager, private val jenisKulit: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val tab_titlle = intArrayOf(R.string.Facewash, R.string.Toner, R.string.Serum)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FacewashFragment().apply {
                arguments = Bundle().apply {
                    putInt(FacewashFragment.EXTRA_FACEWASH, jenisKulit)
                }
            }
            1 -> TonerFragment().apply {
                arguments = Bundle().apply {
                    putInt(TonerFragment.EXTRA_TONER, jenisKulit)
                }
            }
            2 -> SerumFragment().apply {
                arguments = Bundle().apply {
                    putInt(SerumFragment.EXTRA_SERUM, jenisKulit)
                }
            }
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(tab_titlle[position])

    override fun getCount(): Int = 3
}