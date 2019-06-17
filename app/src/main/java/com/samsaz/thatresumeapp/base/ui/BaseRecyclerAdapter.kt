package com.samsaz.thatresumeapp.base.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    var list: List<T> = ArrayList()

    fun setItems(items: List<T>) {
        list = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    fun getItem(position: Int): T {
        return list[position]
    }

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }

}