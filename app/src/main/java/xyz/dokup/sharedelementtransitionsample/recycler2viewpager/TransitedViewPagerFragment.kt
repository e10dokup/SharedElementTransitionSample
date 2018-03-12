package xyz.dokup.sharedelementtransitionsample.recycler2viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_transited_view_pager.*
import xyz.dokup.sharedelementtransitionsample.R
import xyz.dokup.sharedelementtransitionsample.addOnetimeOnPreDrawListener
import xyz.dokup.sharedelementtransitionsample.list.Item

/**
 * Created by e10dokup on 2018/03/11.
 */
class TransitedViewPagerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_transited_view_pager, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments.getSerializable(EXTRA_ITEM) as Item
        val transitionName = arguments.getString(EXTRA_TRANSITION_NAME)
        if (transitionName != null) {
            target_view.transitionName = transitionName
            target_view.addOnetimeOnPreDrawListener {
                activity?.supportStartPostponedEnterTransition()
            }
        }
        text.text = item.name
    }

    companion object {
        private const val EXTRA_ITEM = "item"
        private const val EXTRA_TRANSITION_NAME = "transition"

        fun newInstance(transitionName: String?, item: Item): TransitedViewPagerFragment {
            val fragment = TransitedViewPagerFragment()
            val bundle = Bundle()
            if (transitionName != null) {
                bundle.putString(EXTRA_TRANSITION_NAME, transitionName)
            }
            bundle.putSerializable(EXTRA_ITEM, item)

            fragment.arguments = bundle
            return fragment
        }
    }

}