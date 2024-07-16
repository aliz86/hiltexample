package com.example.hiltexample.di

import android.app.Activity
import androidx.activity.ComponentActivity
import com.example.hiltexample.model.testClasses.otherclasses.ETest
import com.example.hiltexample.model.testClasses.otherclasses.FTest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {


    @Provides
    //fun appCompatActivity(activity : Activity) = activity as AppCompatActivity
    fun appCompatActivity(activity : Activity) = activity as ComponentActivity

    @Provides
    fun eTest() = ETest()

    @Provides
    fun fTest() = FTest()
}