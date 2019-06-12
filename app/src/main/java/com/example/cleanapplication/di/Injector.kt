package com.example.cleanapplication.di

import android.support.v4.app.Fragment

interface Injector<T> {

    fun inject(instance: T)
}

@Suppress("UNCHECKED_CAST")
fun Injector<*>.cast() = this as Injector<Fragment>