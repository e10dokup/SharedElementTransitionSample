package xyz.dokup.sharedelementtransitionsample.basic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_basic_transition.*
import xyz.dokup.sharedelementtransitionsample.R

/**
 * Created by e10dokup on 2018/03/11.
 */
class BasicTransitionActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_transition)

        target_view.transitionName = TRANSITION_NAME

        transition_button.setOnClickListener {
            BasicTransitedActivity.transit(this, target_view, TRANSITION_NAME)
        }

    }

    companion object {
        private const val TRANSITION_NAME = "target"

        fun transit(activity: Activity) {
            activity.startActivity(Intent(activity, BasicTransitionActivity::class.java))
        }
    }

}