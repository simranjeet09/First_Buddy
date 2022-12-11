package dev.simranjeet.firstbuddy.ui.addpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.simranjeet.firstbuddy.data.repositories.DataRepository
import dev.simranjeet.firstbuddy.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class AddPostViewModelFactory(
    private val repository: DataRepository,
    private val userRes: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddPostViewModel(repository,userRes) as T
    }

}
