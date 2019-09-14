package com.example.artworkpool.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Artwork(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("tags") val tags: String,
        @field:SerializedName("user") val user: String,
        @field:SerializedName("largeImageURL") val url: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(tags)
        writeString(user)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Artwork> = object : Parcelable.Creator<Artwork> {
            override fun createFromParcel(source: Parcel): Artwork = Artwork(source)
            override fun newArray(size: Int): Array<Artwork?> = arrayOfNulls(size)
        }
    }
}