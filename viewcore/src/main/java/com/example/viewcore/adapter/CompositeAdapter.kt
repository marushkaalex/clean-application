package com.example.viewcore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class CompositeAdapter : RecyclerView.Adapter<CompositeAdapter.ViewHolder>() {

    val data = arrayListOf<Any>()
    val componentMap = ArrayMap<Class<out Any>, IAdapterComponent<ViewDataBinding, Any>>()
    private var inflater: LayoutInflater? = null

    inline fun <reified D : Any> addComponent(component: IAdapterComponent<ViewDataBinding, Any>) {
        componentMap[D::class.java] = component
    }

    private fun createInflater(context: Context): LayoutInflater = LayoutInflater.from(context).also { inflater = it }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val component = componentMap.valueAt(viewType)
        return ViewHolder(
            DataBindingUtil.inflate(
                inflater ?: createInflater(parent.context),
                component.layoutRes,
                parent,
                false
            )
        ) {
            component.onRecycle(it)
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        val component = componentMap[data[index]::class.java]!!
        component.bind(viewHolder.binding, data[index])
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.recycle.invoke(holder.binding)
    }

    override fun getItemViewType(position: Int): Int {
        val clazz = data[position]::class.java
        val type = componentMap.indexOfKey(clazz)
        if (type < 0) throw IllegalStateException("CompositeAdapter does not contain a component for $clazz. Please add it via CompositeAdapter.addComponent()")
        return type
    }

    class ViewHolder(val binding: ViewDataBinding, val recycle: (ViewDataBinding) -> Unit) :
        RecyclerView.ViewHolder(binding.root)
}