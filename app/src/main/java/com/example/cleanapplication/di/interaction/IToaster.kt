package com.example.cleanapplication.di.interaction

import androidx.annotation.UiThread

interface IToaster {

    @UiThread
    fun showShortToast(message: String)
    @UiThread
    fun showLongToast(message: String)
}