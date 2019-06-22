package com.marciarocha.dormmanager.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marciarocha.dormmanager.R
import com.marciarocha.dormmanager.domain.model.Dorm
import com.marciarocha.dormmanager.ui.bedpicker.BedPickerFragment
import com.marciarocha.dormmanager.ui.bedpicker.OnDialogResultListener
import com.marciarocha.dormmanager.ui.checkout.CheckoutActivity
import com.marciarocha.dormmanager.ui.main.state.AvailableDormsState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

const val TOTAL_PRICE = "total-price"

class MainActivity : AppCompatActivity(), OnDialogResultListener {

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

        checkout_button.setOnClickListener { onCheckout() }
    }

    private fun observeViewModelChanges() {
        viewModel.availableDormsState.observe(this, Observer { availableDormsState ->
            when (availableDormsState) {
                is AvailableDormsState.Loading -> {
                    progress_wheel_dormslist.visibility = View.VISIBLE
                    info_about_prices.visibility = View.GONE
                    error_message.visibility = View.GONE
                }
                is AvailableDormsState.Loaded -> {
                    info_about_prices.visibility = View.VISIBLE
                    error_message.visibility = View.GONE
                    progress_wheel_dormslist.visibility = View.GONE
                    adapter.update(availableDormsState.dorms.toMutableList())
                }
                is AvailableDormsState.Error -> {
                    error_message.visibility = View.VISIBLE
                    progress_wheel_dormslist.visibility = View.GONE
                    info_about_prices.visibility = View.GONE
                }
            }
        })

        viewModel.totalCost.observe(this, Observer {
            checkout_button.visibility = it.visibility
            checkout_button.text = getString(R.string.checkout_button) + " ${it.price}"
        })
    }

    private fun onSelectDorm(dorm: Dorm) {
        BedPickerFragment.newInstance(dorm).show(supportFragmentManager, "BPF")
    }

    override fun onDialogResult(dorm: Dorm, result: Int) {
        viewModel.onDormSelected(dorm, result)
    }

    private fun onCheckout() {
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.putExtra(TOTAL_PRICE, 123.90)
        startActivityForResult(intent, CHECKOUT_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CHECKOUT_REQ_CODE) {
            when (resultCode) {
                Activity.RESULT_CANCELED -> {
                    Log.i("CheckoutActivity", "Result canceled")
                }
                Activity.RESULT_OK -> {
                    viewModel.clearSelectedDorms()
                }
            }
        }
    }

    companion object {
        const val CHECKOUT_REQ_CODE = 1000
    }
}
