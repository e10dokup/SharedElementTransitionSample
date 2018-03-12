package xyz.dokup.sharedelementtransitionsample.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import xyz.dokup.sharedelementtransitionsample.R

/**
 * Created by e10dokup on 2018/03/11.
 */
class FragmentTransitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_transition)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, TransitionFragment.newInstance())
                .commit()


    }

    companion object {
        fun transit(activity: Activity) {
            activity.startActivity(Intent(activity, FragmentTransitionActivity::class.java))
        }
    }

}