package com.example.sunny.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "journal")
data class Entry(
    @PrimaryKey(autoGenerate = true)
     val id: Int,
     val date: String,
     val title: String,
     val journalEntry: String,
    val mood: Int,
     val gratitudeList: String,
     val goalList: String) : Parcelable
