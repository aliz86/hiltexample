package com.example.hiltexample.di

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
    fun eTest() = ETest()

    @Provides
    fun fTest() = FTest()
}