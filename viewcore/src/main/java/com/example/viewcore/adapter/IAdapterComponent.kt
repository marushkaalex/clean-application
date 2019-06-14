package com.example.viewcore.adapter

import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes

interface IAdapterComponent<in Binding : ViewDataBinding, in Data : Any> {

    @get:LayoutRes
    val layoutRes: Int

    fun bind(binding: Binding, data: Data)
    fun onRecycle(binding: Binding) {}
}

@Suppress("UNCHECKED_CAST")
fun IAdapterComponent<*, *>.cast() = this as IAdapterComponent<ViewDataBinding, Any>