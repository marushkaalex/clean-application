package com.example.cleanapplication.ui.search

import com.example.cleanapplication.R
import com.example.cleanapplication.model.RepositoryModel
import com.example.viewcore.adapter.IAdapterComponent

class SearchRepositoryItemAdapterComponent : IAdapterComponent<com.example.cleanapplication.databinding.ItemRepositoryBinding, RepositoryModel> {

    override val layoutRes = R.layout.item_repository

    override fun bind(binding: com.example.cleanapplication.databinding.ItemRepositoryBinding, data: RepositoryModel) {
        binding.viewModel = SearchRepositoryItemViewModel(data)
    }
}