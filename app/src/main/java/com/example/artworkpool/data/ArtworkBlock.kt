package com.example.artworkpool.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ArtworkBlock(
        @field:SerializedName("hits") val hits: List<Artwork>
) : Parcelable {
    constructor(source: Parcel) : this(
            ArrayList<Artwork>().apply { source.readList(this, Artwork::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(hits)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ArtworkBlock> = object : Parcelable.Creator<ArtworkBlock> {
            override fun createFromParcel(source: Parcel): ArtworkBlock = ArtworkBlock(source)
            override fun newArray(size: Int): Array<ArtworkBlock?> = arrayOfNulls(size)
        }
    }
}