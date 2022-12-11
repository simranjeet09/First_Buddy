package dev.simranjeet.firstbuddy.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dev.simranjeet.firstbuddy.R
import dev.simranjeet.firstbuddy.databinding.ActivityLoginBinding
import dev.simranjeet.firstbuddy.utils.startHomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()


    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        binding.viewmodel = viewModel



        viewModel.authListener = this
    }

    override fun onStarted() {
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        val sharedPrefFile = "firstBuddy"
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("loggedin", false)
        editor.apply()
        editor.commit()
        progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        viewModel.user?.let {
            startHomeActivity()
        }
    }


}
