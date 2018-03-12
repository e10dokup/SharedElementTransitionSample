package xyz.dokup.sharedelementtransitionsample.recycler

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_recycler_transited.*
import xyz.dokup.sharedelementtransitionsample.R
import xyz.dokup.sharedelementtransitionsample.list.Item

/**
 * Created by e10dokup on 2018/03/11.
 */
class RecyclerTransitedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_transited)

        val transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME)
        target_view.transitionName = transitionName

        val item = intent.getSerializableExtra(EXTRA_ITEM) as Item
        text.text = item.name
    }

    companion object {
        private const val EXTRA_ITEM = "item"
        private const val EXTRA_TRANSITION_NAME = "transition"

        fun transit(activity: Activity, item: Item, targetView: View, transitionName: String) {
            val intent = Intent(activity, RecyclerTransitedActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, targetView, transitionName)
            intent.putExtra(EXTRA_ITEM, item)
            intent.putExtra(EXTRA_TRANSITION_NAME, transitionName)
            activity.startActivity(intent, options.toBundle())
        }
    }

}