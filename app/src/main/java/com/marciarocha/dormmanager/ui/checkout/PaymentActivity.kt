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
import com.marciarocha.dormmanager.ui.checkout.viewmodel.PaymentViewModel
import com.marciarocha.dormmanager.ui.checkout.viewmodel.PaymentViewModelProviderFactory
import com.marciarocha.dormmanager.ui.main.TOTAL_PRICE
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject

class PaymentActivity : AppCompatActivity() {

    private lateinit var viewModel: PaymentViewModel
    @Inject
    lateinit var mainViewModelProviderFactory: PaymentViewModelProviderFactory

    private val totalCost by lazy { intent.extras.getDouble(TOTAL_PRICE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        AndroidInjection.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.actionBar_title_checkoutactivity)

        viewModel = ViewModelProviders.of(this, mainViewModelProviderFactory).get(PaymentViewModel::class.java)
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
                    finish_button.alpha = 0.5f
                    currency_spinner.visibility = View.GONE
                    total_checkout_text.visibility = View.GONE
                }
                is CurrenciesState.Loaded -> {
                    checkout_progressbar.visibility = View.GONE
                    finish_button.isEnabled = true
                    finish_button.alpha = 1f
                    initSpinner(it.currencies)
                    currency_spinner.visibility = View.VISIBLE
                    total_checkout_text.visibility = View.VISIBLE
                    total_checkout_text.text = "$totalCost"
                }
                is CurrenciesState.Error -> {
                    finish_button.isEnabled = false
                    finish_button.alpha = 0.5f
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
