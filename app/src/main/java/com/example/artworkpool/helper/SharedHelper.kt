package com.example.artworkpool.helper

import android.content.Context
import com.example.artworkpool.util.TAG_NAME

class SharedHelper(val context: Context) {

    private val shredPref = context.getSharedPreferences("My pref", Context.MODE_PRIVATE)

    fun saveTag(tag: String){
        shredPref.edit().putString(TAG_NAME, tag).apply()
    }

    fun getTag() = shredPref.getString(TAG_NAME, "")
}