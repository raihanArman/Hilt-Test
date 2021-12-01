package com.raihanarman.testroom.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_tb")
data class Note(
    var nama: String,
    var umur: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)