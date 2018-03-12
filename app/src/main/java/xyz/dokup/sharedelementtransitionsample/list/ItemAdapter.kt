package xyz.dokup.sharedelementtransitionsample.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.dokup.sharedelementtransitionsample.R

/**
 * Created by e10dokup on 2018/03/11.
 */
class ItemAdapter (
        context: Context,
        private val items: List<Item>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return ItemViewHolder(inflater.inflate(R.layout.item_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder ?: return
        val item = items[position]
        val itemViewHolder = holder as ItemViewHolder

        itemViewHolder.name.text = item.name

        itemViewHolder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position, item, it)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.text)
    }

    interface ItemClickListener {
        fun onItemClick(index: Int, item: Item, view: View)
    }
}