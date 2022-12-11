package dev.simranjeet.firstbuddy.ui.post

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import dev.simranjeet.firstbuddy.data.models.DataModal
import dev.simranjeet.firstbuddy.data.models.PostModel
import dev.simranjeet.firstbuddy.data.repositories.DataRepository
import dev.simranjeet.firstbuddy.data.repositories.UserRepository

class PostViewModel(private val repository: DataRepository, private val userRepo: UserRepository) : ViewModel() {

    var data: MutableLiveData<List<PostModel>> = MutableLiveData()
    var dataToFetch: MutableLiveData<String> = MutableLiveData("")
    var email: MutableLiveData<String> = MutableLiveData("")



        init {
            data = MutableLiveData()
            email.value = userRepo.currentUser()?.email

        }

      fun fetchData() {
        repository.fetchPosts(dataToFetch.value.toString()).orderBy("createdat", Query.Direction.DESCENDING).addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                data.value = null
                return@EventListener
            }

            var savedAddressList: MutableList<PostModel> = mutableListOf()
            for (doc in value!!) {
                var addressItem = doc.toObject(PostModel::class.java)
                savedAddressList.add(addressItem)
            }
            data.value = savedAddressList

        })


    }


}

