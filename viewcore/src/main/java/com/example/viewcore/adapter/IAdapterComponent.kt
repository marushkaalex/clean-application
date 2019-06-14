package com.example.viewcore.adapter

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes

interface IAdapterComponent<in Binding : ViewDataBinding, in Data : Any> {

    @get:LayoutRes
    val layoutRes: Int

    fun bind(binding: Binding, data: Data)
    fun onRecycle(binding: Binding) {}
}

fun IAdapterComponent<*, *>.cast() = this as IAdapterComponent<ViewDataBinding, Any>