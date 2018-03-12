package xyz.dokup.sharedelementtransitionsample.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_transited.*
import xyz.dokup.sharedelementtransitionsample.R



/**
 * Created by e10dokup on 2018/03/11.
 */
class TransitedFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_transited, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        target_view.transitionName = arguments.getString(EXTRA_TRANSITION_NAME)
    }

    companion object {
        private const val EXTRA_TRANSITION_NAME = "transition"

        fun newInstance(transitionName: String): TransitedFragment {
            val fragment = TransitedFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_TRANSITION_NAME, transitionName)
            fragment.arguments = bundle
            return fragment
        }
    }

}