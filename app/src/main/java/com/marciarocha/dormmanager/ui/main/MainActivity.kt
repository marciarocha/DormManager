package com.marciarocha.dormmanager.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marciarocha.dormmanager.R
import com.marciarocha.dormmanager.domain.model.Dorm
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    @Inject
    lateinit var mainViewModelProviderFactory: MainViewModelProviderFactory

    private val adapter = DormListAdapter(this::onSelectDorm)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        dorms_list.adapter = adapter

        viewModel = ViewModelProviders.of(this, mainViewModelProviderFactory).get(MainViewModel::class.java)
        observeViewModelChanges()

        viewModel.getDorms()
    }

    private fun observeViewModelChanges() {
        viewModel.availableDormsState.observe(this, Observer { availableDormsState ->
            when (availableDormsState) {
                is AvailableDormsState.Loading -> {
                }
                is AvailableDormsState.Loaded -> {
                    adapter.update(availableDormsState.dorms.toMutableList())
                }

            }
        })
    }

    private fun onSelectDorm(dorm: Dorm) {

    }
}
