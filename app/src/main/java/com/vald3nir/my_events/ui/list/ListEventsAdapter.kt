package com.vald3nir.my_events.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vald3nir.data.models.ItemListEvents
import com.vald3nir.my_events.databinding.ListEventItemViewBinding

class ListEventsAdapter(val listener: IListEventsAdapter) :
    RecyclerView.Adapter<ListEventsAdapter.ListEventsViewHolder>() {

    var events: List<ItemListEvents> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEventsViewHolder {
        val view = ListEventItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListEventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListEventsViewHolder, position: Int) {
        getItemId(position).let { holder.bind(events[position]) }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    inner class ListEventsViewHolder(private val itemBinding: ListEventItemViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(itemListEvents: ItemListEvents) {
            itemBinding.itemListEvents = itemListEvents
            itemBinding.listEventsListener = View.OnClickListener {
                listener.seeDetail(itemListEvents.id)
            }
        }
    }

    interface IListEventsAdapter {
        fun seeDetail(id: String?)
    }
}
