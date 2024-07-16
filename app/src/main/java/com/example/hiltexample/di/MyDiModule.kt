package com.example.hiltexample.di

import com.example.hiltexample.model.UserRepositoryImp
import com.example.hiltexample.model.testClasses.otherclasses.ETest
import com.example.hiltexample.model.testClasses.otherclasses.FTest
import com.example.hiltexample.model.retrofit.RetrofitApi
import com.example.hiltexample.model.testClasses.otherclasses.DTest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class MyDiModule (/*i : Int*/) {

    @Provides
    fun userRepository() = UserRepositoryImp()

    @Provides
    fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        //.addConverterFactory(GsonConverterFactory.create()) :Must add the dependency to build.gradle
        .build()


    //If I write it like below, every time the DTest object is needed the retrofit object will be recreated because of the getRetrofit()...
/*
    @Provides
    fun dTest() = DTest(getRetrofit().create(Retrofit::class.java))
*/

    //must be added to the component, too : ApplicationComponent & Yam Component
    @Singleton
    @Provides
    fun dTest(retrofit : Retrofit) = DTest(retrofit.create(RetrofitApi::class.java))


}