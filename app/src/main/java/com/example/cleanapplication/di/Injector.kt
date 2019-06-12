package com.example.cleanapplication.di

interface Injector<T> {

    fun inject(instance: T)
}

@Suppress("UNCHECKED_CAST")
fun <T> Injector<*>.cast(): Injector<T> {
    return this as Injector<T>
}