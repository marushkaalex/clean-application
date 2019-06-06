package com.example.cleanapplication

import android.os.Bundle
import com.example.cleanapplication.mvi.EventSourceActivity

class MainActivity : EventSourceActivity<Any>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
