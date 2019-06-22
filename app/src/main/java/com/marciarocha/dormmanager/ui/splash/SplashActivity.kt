package com.marciarocha.dormmanager.ui.splash


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marciarocha.dormmanager.R
import com.marciarocha.dormmanager.ui.main.MainActivity
import dagger.android.AndroidInjection
import javax.inject.Inject


class SplashActivity : AppCompatActivity() {

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
        Handler().postDelayed(Runnable {
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 1000)

    }

    override fun onStart() {
        super.onStart()
        viewModel.populateDatabaseIfEmpty()
    }
}
