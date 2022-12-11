package dev.simranjeet.firstbuddy.data.models

import com.google.firebase.Timestamp
import java.io.Serializable
import java.util.*

class PostModel(var title: String, var description:String,  var createdat:Timestamp, var image:String?, var email:String? ):Serializable{
    constructor():this("","", Timestamp(Date()),"","")
}

