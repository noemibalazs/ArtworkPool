package com.example.artworkpool.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [ArtEntity::class], version = 2, exportSchema = false)
abstract class ArtDataBase: RoomDatabase() {

    abstract fun artDao(): ArtDAO

}