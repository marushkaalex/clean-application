package com.example.cleanapplication.ui.fragment

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleanapplication.ui.BaseDaggerFragment
import javax.inject.Inject

abstract class ViewModelFragment<VM : ViewModel, Binding : ViewDataBinding> :
    BaseDaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val viewModelClass: Class<VM>
    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vm = ViewModelProviders.of(this, viewModelFactory)[viewModelClass]
        val binding = DataBindingUtil.inflate<Binding>(inflater, layoutId, container, false)
        configure(vm, binding)
        binding.lifecycleOwner = this
        // todo set var
        binding.executePendingBindings()
        return binding.root
    }

    abstract fun configure(viewModel: VM, binding: Binding)
}