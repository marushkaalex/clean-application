package com.example.cleanapplication.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.cleanapplication.di.interaction.Injector
import com.example.cleanapplication.ui.activity.IActivityComponent

abstract class BaseDaggerFragment : ScopedFragment() {

    abstract fun getInjector(activityComponent: IActivityComponent<*>): Injector<Fragment>

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getInjector((activity as BaseDaggerActivity).component).inject(this)
    }
}