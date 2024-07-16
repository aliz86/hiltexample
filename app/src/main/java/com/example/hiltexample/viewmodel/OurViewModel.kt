package com.example.hiltexample.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hiltexample.model.UserRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class OurViewModel @Inject constructor(private val userRepository: UserRepository,
    @Assisted savedStateHandle: SavedStateHandle
    )  : ViewModel() {

        fun doSomething(){

        }
}