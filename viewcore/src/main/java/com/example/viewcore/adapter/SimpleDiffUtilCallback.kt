package com.example.viewcore.adapter

import android.support.v7.util.DiffUtil

open class SimpleDiffUtilCallback(private val oldData: List<Any>, private val newData: List<Any>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean = oldData[p0] === newData[p1]

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean = oldData[p0] == newData[p1]
}