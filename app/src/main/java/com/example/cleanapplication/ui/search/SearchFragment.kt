package com.example.cleanapplication.ui.search

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.cleanapplication.R
import com.example.cleanapplication.di.Injector
import com.example.cleanapplication.di.cast
import com.example.cleanapplication.ui.BaseDaggerActivity
import com.example.cleanapplication.ui.BaseDaggerFragment
import com.example.cleanapplication.ui.activity.IActivityComponent
import com.example.cleanapplication.ui.fragment.ViewModelFragment
import javax.inject.Inject

class SearchFragment : ViewModelFragment<SearchFragmentViewModel, com.example.cleanapplication.databinding.FragmentSearchBinding>() {

    override val viewModelClass: Class<SearchFragmentViewModel> = SearchFragmentViewModel::class.java
    override val layoutId: Int = R.layout.fragment_search

    override fun configure(
        viewModel: SearchFragmentViewModel,
        binding: com.example.cleanapplication.databinding.FragmentSearchBinding
    ) {
    }

    @Inject
    lateinit var test: String

    override fun getInjector(activityComponent: IActivityComponent<*>): Injector<Fragment> = DaggerSearchFragmentComponent.builder().iActivityComponent(activityComponent).build().cast()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("kek $test")
    }
}