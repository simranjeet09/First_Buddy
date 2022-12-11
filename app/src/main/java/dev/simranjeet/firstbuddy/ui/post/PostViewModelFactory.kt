package dev.simranjeet.firstbuddy.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.simranjeet.firstbuddy.data.repositories.DataRepository
import dev.simranjeet.firstbuddy.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class PostViewModelFactory(
    private val repository: DataRepository,
    private val user: UserRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(repository,user) as T
    }

}
