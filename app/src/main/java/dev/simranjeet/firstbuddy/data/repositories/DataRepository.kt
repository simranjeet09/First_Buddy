package dev.simranjeet.firstbuddy.data.repositories

import dev.simranjeet.firstbuddy.data.firebase.FirebaseSource

class DataRepository (private val firebase: FirebaseSource){
    //repositories for data manipulation on firestore
    fun fetchCategories() = firebase.fetchCategories();
    fun fetchPosts(  url:String) = firebase.getPost(url);
    fun createPost() = firebase.addPost();

}