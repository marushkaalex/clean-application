package com.example.cleanapplication.binding

import android.databinding.BindingAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.example.viewcore.adapter.CompositeAdapter
import com.example.viewcore.adapter.SimpleDiffUtilCallback

@BindingAdapter("compositeItems")
fun RecyclerView.compositeItems(items: List<out Any>?) {
    (adapter as? CompositeAdapter)?.let {
        if (items.isNullOrEmpty()) {
            it.data.clear()
            return
        }
        val diff = DiffUtil.calculateDiff(SimpleDiffUtilCallback(it.data, items))
        it.data.clear()
        it.data.addAll(items)
        diff.dispatchUpdatesTo(it)
    }
}