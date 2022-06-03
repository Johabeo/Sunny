package com.example.sunny.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Each user has a "Journal" of entries, this is designed to help them reflect according to CBT
techniques. Mood is an int to test field level validation and will be converted into an button
set in future iterations.
 */
@Entity(tableName = "journal")
data class Entry(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "date")val date: String,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "journalentry")val journalEntry: String,
    @ColumnInfo(name = "mood")val mood: Int,
    @ColumnInfo(name = "gratitudelist")val gratitudeList: String,
    @ColumnInfo(name = "goallist")val goalList: String)
