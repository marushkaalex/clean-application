package com.example.cleanapplication.ui.search

import android.view.inputmethod.EditorInfo
import com.example.cleanapplication.R
import com.example.cleanapplication.databinding.FragmentSearchBinding
import com.example.cleanapplication.di.interaction.Injector
import com.example.cleanapplication.di.interaction.cast
import com.example.cleanapplication.ui.activity.IActivityComponent
import com.example.cleanapplication.ui.fragment.ViewModelFragment
import com.example.viewcore.adapter.CompositeAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class SearchFragment :
    ViewModelFragment<SearchFragmentViewModel, FragmentSearchBinding>() {

    @Inject
    lateinit var adapter: CompositeAdapter

    override val viewModelClass: Class<SearchFragmentViewModel> = SearchFragmentViewModel::class.java
    override val layoutId: Int = R.layout.fragment_search

    override fun configure(
        viewModel: SearchFragmentViewModel,
        binding: FragmentSearchBinding
    ) {
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        binding.query.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.search(v.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    override fun getInjector(activityComponent: IActivityComponent<*>): Injector<androidx.fragment.app.Fragment> =
        DaggerSearchFragmentComponent.builder().iActivityComponent(activityComponent).build().cast()
}