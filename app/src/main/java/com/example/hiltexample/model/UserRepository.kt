package com.example.hiltexample.model

interface UserRepository {
    fun getUsers() : List<User>
}