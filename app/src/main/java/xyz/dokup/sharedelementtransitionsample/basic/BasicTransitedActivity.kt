package xyz.dokup.sharedelementtransitionsample.basic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_basic_transited.*
import xyz.dokup.sharedelementtransitionsample.R

/**
 * Created by e10dokup on 2018/03/11.
 */
class BasicTransitedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_transited)

        val transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME)

        target_view.transitionName = transitionName
    }

    companion object {
        private const val EXTRA_TRANSITION_NAME = "transition"

        fun transit(activity: Activity, targetView: View, transitionName: String) {
            val intent = Intent(activity, BasicTransitedActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, targetView, transitionName)
            intent.putExtra(EXTRA_TRANSITION_NAME, transitionName)
            activity.startActivity(intent, options.toBundle())
        }
    }

}