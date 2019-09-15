package com.example.artworkpool.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Artwork @JvmOverloads constructor(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("tags") val tags: String,
        @field:SerializedName("user") val user: String,
        @field:SerializedName("largeImageURL") val url: String,
        @field:SerializedName("pageURL") val pageUrl: String = "",
        @field:SerializedName("type") val type: String = "",
        @field:SerializedName("previewURL") val previewURL: String = "",
        @field:SerializedName("previewWidth") val previewWidth: Int = 0,
        @field:SerializedName("previewHeight") val previewHeight: Int = 0,
        @field:SerializedName("webformatURL") val webformatURL: String = "",
        @field:SerializedName("webformatWidth") val webformatWidth: Int = 0,
        @field:SerializedName("webformatHeight") val webformatHeight: Int = 0,
        @field:SerializedName("fullHDURL") val fullHDURL: String = "",
        @field:SerializedName("imageURL") val imageURL: String = "",
        @field:SerializedName("imageWidth") val imageWidth: Int = 0,
        @field:SerializedName("imageHeight") val imageHeight: Int = 0,
        @field:SerializedName("imageSize") val imageSize: Int = 0,
        @field:SerializedName("views") val views: Int = 0,
        @field:SerializedName("downloads") val downloads: Int = 0,
        @field:SerializedName("favorites") val favorites: Int = 0,
        @field:SerializedName("likes") val likes: Int = 0,
        @field:SerializedName("comments") val comments: Int = 0,
        @field:SerializedName("user_id") val userId: Int = 0,
        @field:SerializedName("userImageURL") val userImageURL: String = ""

) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(tags)
        writeString(user)
        writeString(url)
        writeString(pageUrl)
        writeString(type)
        writeString(previewURL)
        writeInt(previewWidth)
        writeInt(previewHeight)
        writeString(webformatURL)
        writeInt(webformatWidth)
        writeInt(webformatHeight)
        writeString(fullHDURL)
        writeString(imageURL)
        writeInt(imageWidth)
        writeInt(imageHeight)
        writeInt(imageSize)
        writeInt(views)
        writeInt(downloads)
        writeInt(favorites)
        writeInt(likes)
        writeInt(comments)
        writeInt(userId)
        writeString(userImageURL)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Artwork> = object : Parcelable.Creator<Artwork> {
            override fun createFromParcel(source: Parcel): Artwork = Artwork(source)
            override fun newArray(size: Int): Array<Artwork?> = arrayOfNulls(size)
        }
    }
}