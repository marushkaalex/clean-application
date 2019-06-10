package com.example.cleanapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryListModel(
    @SerializedName("total_count")
    val totalCount: Long = 0,
    val items: List<RepositoryModel> = emptyList()
) : Parcelable

@Parcelize
data class RepositoryModel(
    val id: Long = 0,
    val name: String = ""
) : Parcelable