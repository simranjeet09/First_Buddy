package dev.simranjeet.firstbuddy.data.repositories

import dev.simranjeet.firstbuddy.data.firebase.FirebaseSource

class DataRepository (private val firebase: FirebaseSource){
    fun fetchCategories() = firebase.getSavedAddress();
    fun fetchPosts(  url:String) = firebase.getPost(url);
    fun createPost() = firebase.addPost();

}