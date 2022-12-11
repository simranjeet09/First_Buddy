package dev.simranjeet.firstbuddy.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.simranjeet.firstbuddy.R
import dev.simranjeet.firstbuddy.ui.auth.AuthViewModel
import dev.simranjeet.firstbuddy.ui.auth.AuthViewModelFactory
import dev.simranjeet.firstbuddy.ui.auth.LoginActivity
import kotlinx.android.synthetic.main.fragment_contact_us.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ContactUs : Fragment(), KodeinAware {
    private val factory: AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel
    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_contact_us, container, false)
        viewModel = ViewModelProvider(requireActivity(), factory)[AuthViewModel::class.java]

        view.email.text = "Logged in using " + viewModel.user?.email
        view.logout.setOnClickListener {
            viewModel.logout()
            requireActivity().finishAffinity()
            Intent(view.context, LoginActivity::class.java).also {
                view.context.startActivity(it)
            }

        }
        return view
    }


}