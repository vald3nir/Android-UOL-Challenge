package com.vald3nir.my_events.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vald3nir.data.models.EventItemView
import com.vald3nir.my_events.databinding.ListEventItemViewBinding

class ListEventsAdapter(val listener: IListEventsAdapter) :
    RecyclerView.Adapter<ListEventsAdapter.ListEventsViewHolder>() {

    var events: List<EventItemView> = listOf()
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

        fun bind(eventItemView: EventItemView) {
            itemBinding.itemView = eventItemView
            itemBinding.listEventsListener = View.OnClickListener {
                listener.seeDetail(eventItemView.id)
            }
        }
    }

    interface IListEventsAdapter {
        fun seeDetail(id: String?)
    }
}
