package dev.simranjeet.firstbuddy

import android.app.Application
import dev.simranjeet.firstbuddy.data.firebase.FirebaseSource
import dev.simranjeet.firstbuddy.data.repositories.DataRepository
import dev.simranjeet.firstbuddy.data.repositories.UserRepository
import dev.simranjeet.firstbuddy.ui.addpost.AddPostViewModelFactory
import dev.simranjeet.firstbuddy.ui.auth.AuthViewModelFactory
import dev.simranjeet.firstbuddy.ui.home.HomeViewModelFactory
import dev.simranjeet.firstbuddy.ui.post.PostViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class FirebaseApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplication))
        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { DataRepository(instance()) }
        bind() from provider { PostViewModelFactory(instance(), instance()) }
        bind() from provider { AddPostViewModelFactory(instance(), instance()) }

    }
}