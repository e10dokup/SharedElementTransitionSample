package xyz.dokup.sharedelementtransitionsample.recycler

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_recycler.*
import xyz.dokup.sharedelementtransitionsample.R
import xyz.dokup.sharedelementtransitionsample.list.Item
import xyz.dokup.sharedelementtransitionsample.list.ItemAdapter

class RecyclerViewActivity : AppCompatActivity() {
    private val itemAdapter by lazy { ItemAdapter(this, items) }

    private val items = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = itemAdapter
        itemAdapter.itemClickListener = object : ItemAdapter.ItemClickListener {
            override fun onItemClick(index: Int, item: Item, view: View) {
                val target = view.findViewById<View>(R.id.target_view)
                target.transitionName = TRANSITION_NAME
                RecyclerTransitedActivity.transit(this@RecyclerViewActivity, item, target, TRANSITION_NAME)
            }
        }
        createItemList()
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

    companion object {
        private const val TRANSITION_NAME = "target"

        fun transit(activity: Activity) {
            activity.startActivity(Intent(activity, RecyclerViewActivity::class.java))
        }
    }
}
