package dev.simranjeet.firstbuddy.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.simranjeet.firstbuddy.data.repositories.DataRepository
import dev.simranjeet.firstbuddy.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val repository: DataRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }

}
