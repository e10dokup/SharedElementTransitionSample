package xyz.dokup.sharedelementtransitionsample.recycler2viewpager

import android.app.Activity
import android.app.SharedElementCallback
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_recycler_to_view_pager.*
import xyz.dokup.sharedelementtransitionsample.R
import xyz.dokup.sharedelementtransitionsample.list.Item
import xyz.dokup.sharedelementtransitionsample.list.ItemAdapter

class RecyclerViewToViewPagerActivity : AppCompatActivity() {
    private val itemAdapter by lazy { ItemAdapter(this, items) }

    private val items = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_to_view_pager)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = itemAdapter
        itemAdapter.itemClickListener = object : ItemAdapter.ItemClickListener {
            override fun onItemClick(index: Int, item: Item, view: View) {
                ViewPagerActivity.currentPosition = index
                val target = view.findViewById<View>(R.id.target_view)
                target.transitionName = TRANSITION_NAME
                ViewPagerActivity.transit(this@RecyclerViewToViewPagerActivity, item, target, TRANSITION_NAME)
            }
        }
        createItemList()
        ViewPagerActivity.currentPosition = 0

        prepareTransition()
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        scrollToPosition()
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

        itemAdapter.notifyDataSetChanged()
    }

    private fun prepareTransition() {
        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                val viewHolder = recycler_view.findViewHolderForAdapterPosition(ViewPagerActivity.currentPosition)
                if (viewHolder?.itemView == null || names == null) {
                    return
                }
                names.clear()
                sharedElements?.clear()
                names.add(TRANSITION_NAME + "_return")
                sharedElements?.put(TRANSITION_NAME + "_return", viewHolder.itemView.findViewById(R.id.target_view))
            }
        })
    }

    private fun scrollToPosition() {
        supportPostponeEnterTransition()
        val layoutManager = recycler_view.layoutManager
        val viewAtPosition = layoutManager.findViewByPosition(ViewPagerActivity.currentPosition)
        if (viewAtPosition == null || layoutManager.isViewPartiallyVisible(viewAtPosition, false, true)) {
            recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    recycler_view.removeOnScrollListener(this)
                    prepareTransition()
                    supportStartPostponedEnterTransition()
                }
            })
            recycler_view.scrollToPosition(ViewPagerActivity.currentPosition)
        } else {
            prepareTransition()
            supportStartPostponedEnterTransition()
        }
    }

    companion object {
        private const val TRANSITION_NAME = "target"

        fun transit(activity: Activity) {
            activity.startActivity(Intent(activity, RecyclerViewToViewPagerActivity::class.java))
        }
    }
}
