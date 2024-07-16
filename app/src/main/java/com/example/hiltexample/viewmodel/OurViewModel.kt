package com.example.hiltexample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hiltexample.model.UserRepository
import javax.inject.Inject


class OurViewModel @Inject constructor(private val userRepository: UserRepository)  : ViewModel() {


}