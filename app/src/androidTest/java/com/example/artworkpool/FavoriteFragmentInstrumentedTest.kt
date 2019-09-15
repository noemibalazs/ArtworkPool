package com.example.artworkpool

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.artworkpool.adapter.ArtWorkAdapter
import com.example.artworkpool.fragment.FavoriteFragment
import com.example.artworkpool.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentInstrumentedTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setFragment(){
        rule.activity.runOnUiThread{
           rule.activity.supportFragmentManager.beginTransaction().replace(R.id.frame, FavoriteFragment()).commit()
        }
    }

    @Test
    fun clickRv(){
        onView(withId(R.id.favRecycler)).perform(RecyclerViewActions.actionOnItemAtPosition<ArtWorkAdapter.ArtVH>(1, click()))
    }

}