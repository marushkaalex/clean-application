package com.example.cleanapplication.util

import kotlinx.coroutines.Job

fun Job?.cancelJob() {
    this?.let {
        if (it.isActive) it.cancel()
    }
}