package xyz.dokup.sharedelementtransitionsample.recycler2viewpager

import android.app.Activity
import android.app.SharedElementCallback
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_view_pager.*
import xyz.dokup.sharedelementtransitionsample.R
import xyz.dokup.sharedelementtransitionsample.list.Item

class ViewPagerActivity : AppCompatActivity() {
    private val items = ArrayList<Item>()

    private lateinit var item: Item
    private lateinit var transitionName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        supportPostponeEnterTransition()

        item = intent.getSerializableExtra(EXTRA_ITEM) as Item
        transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME)

        createItemList()

        view_pager.adapter = TransitedFragmentPagerAdapter(supportFragmentManager, items, transitionName, item.id)

        view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                currentPosition = position
                setResult(Activity.RESULT_OK)
            }
        })
        view_pager.setCurrentItem(currentPosition, false)

        prepareTransition()
    }

    private fun createItemList() {
        for(i in 1 .. 25) {
            when {
                i <= 5 -> items.add(Item(i, "AAA"))
                i in 6..10 -> items.add(Item(i, "BBB"))
                i in 11..15 -> items.add(Item(i, "CCC"))
                i in 16..20 -> items.add(Item(i, "DDD"))
                i > 20 -> items.add(Item(i, "EEE"))
            }
        }
    }

    private fun prepareTransition() {
        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                val fragment = view_pager.adapter.instantiateItem(view_pager, currentPosition) as Fragment
                val view = fragment.view
                view ?: return
                names ?: return
                names.clear()
                sharedElements?.clear()
                names.add(transitionName + "_return")
                sharedElements?.put(transitionName + "_return", view.findViewById(R.id.target_view))
            }
        })
    }

    companion object {
        private const val REQUEST_CODE = 9000

        private const val EXTRA_ITEM = "item"
        private const val EXTRA_TRANSITION_NAME = "transition"

        var currentPosition = 0

        fun transit(activity: Activity, item: Item, targetView: View, transitionName: String) {
            val intent = Intent(activity, ViewPagerActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, targetView, transitionName)
            intent.putExtra(EXTRA_ITEM, item)
            intent.putExtra(EXTRA_TRANSITION_NAME, transitionName)
            activity.startActivityForResult(intent, REQUEST_CODE, options.toBundle())
        }
    }
}
