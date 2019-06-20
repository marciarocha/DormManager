package com.marciarocha.dormmanager.ui.splash


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marciarocha.dormmanager.R
import com.marciarocha.dormmanager.ui.main.MainActivity
import dagger.android.AndroidInjection
import javax.inject.Inject


class SplashScreen : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel
    @Inject
    lateinit var splashViewModelProviderFactory: SplashViewModelProviderFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, splashViewModelProviderFactory).get(SplashViewModel::class.java)

        observeChanges()
    }

    private fun observeChanges() {
        viewModel.databaseState.observe(this, Observer {
            when (it) {
                is DatabaseState.DatabaseLoaded -> {
                    goToMainScreen()
                }
                is DatabaseState.Error -> {
                }
            }
        })
    }

    private fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        viewModel.populateDatabaseIfEmpty()
    }
}
