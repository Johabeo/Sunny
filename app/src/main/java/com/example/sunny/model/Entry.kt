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
    @ColumnInfo val id: Int?,
    @ColumnInfo val date: String?,
    @ColumnInfo val title: String?,
    @ColumnInfo val journalEntry: String?,
    @ColumnInfo val mood: Int?,
    @ColumnInfo val gratitudeList: String?,
    @ColumnInfo val goalList: String?) : Parcelable
