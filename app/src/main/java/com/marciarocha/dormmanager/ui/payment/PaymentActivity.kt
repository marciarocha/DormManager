package com.marciarocha.dormmanager.ui.payment

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marciarocha.dormmanager.R
import com.marciarocha.dormmanager.ui.main.TOTAL_PRICE
import com.marciarocha.dormmanager.ui.payment.state.ConversionState
import com.marciarocha.dormmanager.ui.payment.state.CurrenciesState
import com.marciarocha.dormmanager.ui.payment.viewmodel.PaymentViewModel
import com.marciarocha.dormmanager.ui.payment.viewmodel.PaymentViewModelProviderFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_payment.*
import javax.inject.Inject

class PaymentActivity : AppCompatActivity() {

    private lateinit var viewModel: PaymentViewModel
    @Inject
    lateinit var mainViewModelProviderFactory: PaymentViewModelProviderFactory

    private val totalCost by lazy { intent.extras.getDouble(TOTAL_PRICE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        AndroidInjection.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.actionBar_title_checkoutactivity)

        viewModel = ViewModelProviders.of(this, mainViewModelProviderFactory).get(PaymentViewModel::class.java)
        observeViewModelChanges()

        finish_button.setOnClickListener { onFinishPayment() }
    }

    private fun observeViewModelChanges() {
        viewModel.currenciesState.observe(this, Observer {
            when (it) {
                is CurrenciesState.Loading -> {
                    checkout_progressbar.visibility = View.VISIBLE
                    finish_button.isEnabled = false
                    finish_button.alpha = 0.5f
                    payment_info_cardview.visibility = View.GONE
                }
                is CurrenciesState.Loaded -> {
                    payment_info_cardview.visibility = View.VISIBLE
                    checkout_progressbar.visibility = View.GONE
                    finish_button.isEnabled = true
                    finish_button.alpha = 1f
                    initSpinner(it.currencies)
                    total_checkout_text.text = "$totalCost"
                }
                is CurrenciesState.Error -> {
                    payment_info_cardview.visibility = View.VISIBLE
                    finish_button.isEnabled = true
                    finish_button.alpha = 1f
                    checkout_progressbar.visibility = View.GONE
                    currency_spinner.visibility = View.GONE
                    total_checkout_text.text = "$totalCost"
                    currency_text.text = getString(R.string.USD)
                    currency_text.visibility = View.VISIBLE
                    showToast()
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

    private fun showToast() {
        Toast.makeText(this, getString(R.string.currencies_error), Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        setResult(Activity.RESULT_CANCELED)
        finish()
        return true
    }

    private fun onFinishPayment() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
