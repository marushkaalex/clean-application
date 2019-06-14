package com.example.cleanapplication.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleanapplication.BR
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
        binding.setVariable(BR.viewModel, vm)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    abstract fun configure(viewModel: VM, binding: Binding)
}