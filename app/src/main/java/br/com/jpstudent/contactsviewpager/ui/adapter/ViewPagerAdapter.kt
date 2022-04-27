package br.com.jpstudent.contactsviewpager.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.jpstudent.contactsviewpager.ui.fragments.BasePageFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val fragments: List<BasePageFragment>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = fragments.size


    override fun createFragment(position: Int): Fragment = fragments[position]


}