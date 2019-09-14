package com.example.artworkpool.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.content.Context
import com.example.artworkpool.data.Artwork
import com.example.artworkpool.util.ART_DATA_BASE

@Dao
interface ArtDAO {

    @Query("SELECT * FROM art_entity")
    fun getArtList(): LiveData<MutableList<ArtEntity>>

    @Query("SELECT * FROM art_entity WHERE tag =:tag")
    fun getArtByTag(tag: String): LiveData<ArtEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArt2DB(artwork: ArtEntity)

    companion object{

        fun getArtDao(context: Context):ArtDAO{
            return Room.databaseBuilder(context, ArtDataBase::class.java, ART_DATA_BASE).build().artDao()
        }
    }
}