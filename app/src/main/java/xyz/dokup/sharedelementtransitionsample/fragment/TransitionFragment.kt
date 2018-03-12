package xyz.dokup.sharedelementtransitionsample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_transtition.*
import xyz.dokup.sharedelementtransitionsample.R

/**
 * Created by e10dokup on 2018/03/11.
 */
class TransitionFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_transtition, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        target_view.transitionName = TRANSITION_NAME

        transition_button.setOnClickListener {
            transit()
        }
    }

    private fun transit() {
        fragmentManager.beginTransaction()
                .addSharedElement(target_view, TRANSITION_NAME)
                .replace(R.id.container, TransitedFragment.newInstance(TRANSITION_NAME))
                .addToBackStack(null)
                .commit()
    }

    companion object {
        private const val TRANSITION_NAME = "target"

        fun newInstance(): TransitionFragment {
            return TransitionFragment()
        }
    }

}