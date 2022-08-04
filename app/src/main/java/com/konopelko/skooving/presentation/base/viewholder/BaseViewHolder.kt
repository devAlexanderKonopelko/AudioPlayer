package com.konopelko.skooving.presentation.base.viewholder

import android.content.res.Resources
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    protected open val binding: ViewDataBinding
): RecyclerView.ViewHolder(binding.root) {

    protected val resources: Resources = itemView.resources // can be useful using values from resources

   abstract fun bind(item: T)
}