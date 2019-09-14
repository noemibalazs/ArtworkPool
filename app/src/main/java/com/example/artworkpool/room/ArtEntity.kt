package com.example.artworkpool.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "art_entity")
data class ArtEntity(
        @PrimaryKey
        val tag: String,
        val id: Int,
        val user: String,
        val url: String
)