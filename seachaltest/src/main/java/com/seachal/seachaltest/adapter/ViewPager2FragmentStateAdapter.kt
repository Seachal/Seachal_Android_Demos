package com.xkw.training.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * TODO  用于 Fragment 中嵌套ViewPager2 容纳 Fragment 时使用。
 *
 * @property fragments
 * @constructor
 * TODO
 *
 * @param mFragment
 */
class ViewPager2FragmentStateAdapter(
    mFragment: Fragment,
    private val fragments: MutableList<Fragment>
) :
    FragmentStateAdapter(mFragment) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}