package xyz.dokup.sharedelementtransitionsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import xyz.dokup.sharedelementtransitionsample.basic.BasicTransitionActivity
import xyz.dokup.sharedelementtransitionsample.fragment.FragmentTransitionActivity
import xyz.dokup.sharedelementtransitionsample.recycler.RecyclerViewActivity
import xyz.dokup.sharedelementtransitionsample.recycler2viewpager.RecyclerViewToViewPagerActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        basic_button.setOnClickListener {
            BasicTransitionActivity.transit(this)
        }

        fragment_button.setOnClickListener {
            FragmentTransitionActivity.transit(this)
        }

        recycler_button.setOnClickListener {
            RecyclerViewActivity.transit(this)
        }

        recycler_view_pager_button.setOnClickListener {
            RecyclerViewToViewPagerActivity.transit(this)
        }
    }
}
