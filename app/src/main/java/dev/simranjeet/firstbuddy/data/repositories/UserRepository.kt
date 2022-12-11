package dev.simranjeet.firstbuddy.data.repositories

import dev.simranjeet.firstbuddy.data.firebase.FirebaseSource

class UserRepository (private val firebase: FirebaseSource){
    fun login(email: String, password: String) = firebase.login(email, password)
    fun forgot(email: String) = firebase.forgot(email)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()
}