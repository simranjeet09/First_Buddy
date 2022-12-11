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

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    val db: FirebaseFirestore by lazy {
        Firebase.firestore

    }


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


    fun getSavedAddress(): CollectionReference {
        var collectionReference = db.collection("categories")
        return collectionReference
    }


    fun getPost(s:String): CollectionReference {
        Log.e("uslll",s);
        Log.e("uslll",s);
        Log.e("uslll",s);
        Log.e("uslll",s);
        Log.e("uslll",s);
        var collectionReference = db.collection(s.lowercase().trim())

        return collectionReference
    }
    fun addPost(): CollectionReference {

        var collectionReference = db.collection("public")

        return collectionReference
    }


    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser

}