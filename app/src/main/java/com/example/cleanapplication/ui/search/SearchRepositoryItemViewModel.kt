package com.example.cleanapplication.ui.search

import android.arch.lifecycle.MutableLiveData
import com.example.cleanapplication.model.RepositoryModel

class SearchRepositoryItemViewModel(data: RepositoryModel) {
    val title = MutableLiveData<String>().apply { value = data.name }
}