package dev.simranjeet.firstbuddy.ui.addpost

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.google.firebase.Timestamp
import dev.simranjeet.firstbuddy.R
import dev.simranjeet.firstbuddy.data.repositories.DataRepository
import dev.simranjeet.firstbuddy.data.repositories.UserRepository
import java.util.*


class AddPostViewModel(
    private val repository: DataRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    var title = MutableLiveData<String>("")
    var description = MutableLiveData<String>("")

    val user by lazy {
        userRepo.currentUser()
    }

    fun addPost(context: Context) {

        if(title.value.isNullOrEmpty()){
            Toast.makeText(
                context,
                "Please enter a valid title",
                Toast.LENGTH_SHORT
            ).show()
            return;

        }else if (description.value.isNullOrEmpty()){

            Toast.makeText(
                context,
                "Please enter a valid description",
                Toast.LENGTH_SHORT
            ).show()
            return;
        }

        val docData = hashMapOf(
            "title" to title.value,
            "description" to description.value,
            "email" to user?.email,
            "image" to "",
            "createdat" to Timestamp(Date()),
        )
        repository.createPost().document().set(docData)
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Post added successfully",
                    Toast.LENGTH_SHORT
                ).show()

            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Something went wring please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }


    }


}

