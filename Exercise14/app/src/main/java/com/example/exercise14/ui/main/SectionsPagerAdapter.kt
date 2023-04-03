package com.example.exercise14.ui.main

import android.content.Context
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.exercise14.R
import com.example.exercise14.Tab1
import com.example.exercise14.Tab2
import com.google.android.material.tabs.TabLayout.Tab
import java.util.ArrayList

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private final var fragmentList: ArrayList<Fragment> = ArrayList()
    private final var fragmentTitleList: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return if(position == 0) {
            Tab2();
        } else {
            Tab1();
        }
    }

    fun getTab(position: Int): Fragment {
        return Tab2();
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}