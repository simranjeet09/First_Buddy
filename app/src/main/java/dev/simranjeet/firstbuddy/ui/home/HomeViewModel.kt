package dev.simranjeet.firstbuddy.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import dev.simranjeet.firstbuddy.data.models.DataModal
import dev.simranjeet.firstbuddy.data.repositories.DataRepository

class HomeViewModel(private val repository: DataRepository) : ViewModel() {

    var categories: MutableLiveData<List<DataModal>> = MutableLiveData()

    init {

        fetchDate()
    }

    private fun fetchDate() {
        repository.fetchCategories().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.e("Home viewmodel", "Listen failed.", e)
                categories.value = null
                return@EventListener
            }

            var savedAddressList: MutableList<DataModal> = mutableListOf()
            Log.e("sadasasdasdasd", value?.size().toString())
            for (doc in value!!) {
                var addressItem = doc.toObject(DataModal::class.java)
                savedAddressList.add(addressItem)
            }
            categories.value = savedAddressList

            Log.e("categories", savedAddressList.size.toString())
        })


    }


}

