package com.marciarocha.dormmanager.ui.checkout

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marciarocha.dormmanager.R
import com.marciarocha.dormmanager.ui.checkout.state.ConversionState
import com.marciarocha.dormmanager.ui.checkout.state.CurrenciesState
import com.marciarocha.dormmanager.ui.main.TOTAL_PRICE
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject

class CheckoutActivity : AppCompatActivity() {

    private lateinit var viewModel: CheckoutViewModel
    @Inject
    lateinit var mainViewModelProviderFactory: CheckoutViewModelProviderFactory

    private val totalCost by lazy { intent.extras.getDouble(TOTAL_PRICE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, mainViewModelProviderFactory).get(CheckoutViewModel::class.java)
        observeViewModelChanges()
        viewModel.getRates("USD")

        finish_button.setOnClickListener { onFinishPayment() }
    }

    private fun observeViewModelChanges() {
        viewModel.currenciesState.observe(this, Observer {
            when (it) {
                is CurrenciesState.Loading -> {
                    checkout_progressbar.visibility = View.VISIBLE
                    finish_button.isEnabled = false
                    currency_spinner.visibility = View.GONE
                    total_checkout_text.visibility = View.GONE
                }
                is CurrenciesState.Loaded -> {
                    checkout_progressbar.visibility = View.GONE
                    finish_button.isEnabled = true
                    initSpinner(it.currencies)
                    currency_spinner.visibility = View.VISIBLE
                    total_checkout_text.visibility = View.VISIBLE
                    total_checkout_text.text = "$totalCost"
                }
                is CurrenciesState.Error -> {
                    finish_button.isEnabled = false
                    checkout_progressbar.visibility = View.GONE
                    currency_spinner.visibility = View.GONE
                    total_checkout_text.visibility = View.VISIBLE
                    total_checkout_text.text = "$totalCost USD"
                }
            }
        })

        viewModel.conversionState.observe(this, Observer {
            when (it) {
                is ConversionState.Loaded -> {
                    total_checkout_text.text = "${it.convertedPrice}"
                }
            }
        })
    }

    private fun initSpinner(currencies: List<String>) {
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, currencies)
        currency_spinner.adapter = arrayAdapter

        val startPosition = currencies.indexOf("USD")
        currency_spinner.setSelection(startPosition)

        currency_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.onSelectCurrency(totalCost, parent?.getItemAtPosition(position) as String)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun onFinishPayment() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
