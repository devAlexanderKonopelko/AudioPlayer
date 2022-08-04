package com.konopelko.skooving.presentation.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konopelko.skooving.presentation.base.viewholder.BaseViewHolder

abstract class BaseRecyclerAdapter<T : Any, VH : BaseViewHolder<T>> :
    RecyclerView.Adapter<VH>() {

    protected var items = listOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = getItemLayoutId(viewType)
        return getViewHolder(inflater, layoutId, parent)
    }

    abstract fun getItemLayoutId(viewType: Int): Int

    abstract fun getViewHolder(inflater: LayoutInflater, layoutId: Int, parent: ViewGroup): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}