package dev.simranjeet.firstbuddy.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.Completable


class FirebaseSource {
    // get firebase instance
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    // get firestore instance

    val db: FirebaseFirestore by lazy {
        Firebase.firestore

    }

    // user login implementation

    fun login(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }
    // forgot password implementation

    fun forgot(email: String) = Completable.create { emitter ->
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }
    // register   implementation

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    //   categories reference implementation

    fun fetchCategories(): CollectionReference {
        var collectionReference = db.collection("categories")
        return collectionReference
    }

    //   categories post reference implementation

    fun getPost(s:String): CollectionReference {

        var collectionReference = db.collection(s.lowercase().trim())

        return collectionReference
    }
    //   add post reference implementation

    fun addPost(): CollectionReference {

        var collectionReference = db.collection("public")

        return collectionReference
    }

//logout from firebase
    fun logout() = firebaseAuth.signOut()
//get current logged in user

    fun currentUser() = firebaseAuth.currentUser

}