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
import com.marciarocha.dormmanager.ui.main.adapter.DormListAdapter
import com.marciarocha.dormmanager.ui.main.state.AvailableDormsState
import com.marciarocha.dormmanager.ui.main.viewmodel.MainViewModel
import com.marciarocha.dormmanager.ui.main.viewmodel.MainViewModelProviderFactory
import com.marciarocha.dormmanager.ui.payment.PaymentActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

const val TOTAL_PRICE = "total-price"

class MainActivity : AppCompatActivity(), OnDialogResultListener {

    private lateinit var viewModel: MainViewModel
    @Inject
    lateinit var mainViewModelProviderFactory: MainViewModelProviderFactory

    private val adapter = DormListAdapter(this::onSelectDorm)
    private var totalPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        dorms_list.adapter = adapter

        viewModel = ViewModelProviders.of(this, mainViewModelProviderFactory).get(MainViewModel::class.java)
        observeViewModelChanges()

        checkout_button.setOnClickListener { onCheckout() }
        cancel_button.setOnClickListener { onCancel() }
    }

    private fun observeViewModelChanges() {
        viewModel.availableDormsState.observe(this, Observer { availableDormsState ->
            when (availableDormsState) {
                is AvailableDormsState.Loading -> {
                    progress_bar_mainactivity.visibility = View.VISIBLE
                    error_message.visibility = View.GONE
                }
                is AvailableDormsState.Loaded -> {
                    error_message.visibility = View.GONE
                    progress_bar_mainactivity.visibility = View.GONE
                    adapter.update(availableDormsState.dorms.toMutableList())
                }
                is AvailableDormsState.Error -> {
                    error_message.visibility = View.VISIBLE
                    progress_bar_mainactivity.visibility = View.GONE
                }
            }
        })

        viewModel.bookingState.observe(this, Observer { shoppingCartState ->
            total_cost_cardview.visibility = shoppingCartState.visibility
            checkout_button.text = getString(R.string.checkout_button)
            total_price_textView.text = "${shoppingCartState.price} " + getString(R.string.USD)
            totalPrice = shoppingCartState.price
        })
    }

    private fun onSelectDorm(dorm: Dorm) {
        BedPickerFragment.newInstance(dorm).show(supportFragmentManager, "BPF")
    }

    override fun onDialogResult(dorm: Dorm, result: Int) {
        viewModel.onDormSelected(dorm, result)
    }

    private fun onCheckout() {
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(TOTAL_PRICE, totalPrice)
        startActivityForResult(intent, CHECKOUT_REQ_CODE)
    }

    private fun onCancel() {
        viewModel.clearSelectedDorms()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CHECKOUT_REQ_CODE) {
            when (resultCode) {
                Activity.RESULT_CANCELED -> {
                    Log.i("PaymentActivity", "Result canceled")
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
