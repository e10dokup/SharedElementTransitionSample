package xyz.dokup.sharedelementtransitionsample.recycler2viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import xyz.dokup.sharedelementtransitionsample.list.Item

/**
 * Created by e10dokup on 2018/03/11.
 */
class TransitedFragmentPagerAdapter constructor(
        fragmentManager: FragmentManager,
        private val items: List<Item>,
        private val transitionName: String,
        private val targetId: Int
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val item = items[position]
        val transitionName = if (targetId == item.id) {
            this.transitionName
        } else {
            null
        }
        return TransitedViewPagerFragment.newInstance(transitionName, item)
    }

    override fun getCount(): Int {
        return items.size
    }
}