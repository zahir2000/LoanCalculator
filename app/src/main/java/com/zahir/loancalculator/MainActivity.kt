package com.zahir.loancalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.name
import android.R.id
import android.content.Context
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    val loanText = "Car Loan: "
    val intText = "Interest: "
    val monthlyText = "Monthly Repayment: "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_calculate.setOnClickListener{
            calculateLoan(it)
        }

        button_reset.setOnClickListener {
            reset(it)
        }
    }

    private fun calculateLoan(view: View){
        val carLoan:Double
        val interest:Double
        val monthlyRepayment:Double

        try {
            carLoan = editTextCarPrice.text.toString().toDouble() - editTextCarDownPayment.text.toString().toDouble()
            interest = carLoan * editTextInterestRate.text.toString().toDouble() * editTextLoanPeriod.text.toString().toInt()
            monthlyRepayment = (carLoan + interest)/editTextLoanPeriod.text.toString().toInt()/12

            textViewCarLoan.text = "$loanText %.2f".format(carLoan)
            textViewInterest.text = "$intText %.2f".format(interest)
            textViewMonthlyRepayment.text = "$monthlyText %.2f".format(monthlyRepayment)

            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            Toast.makeText(applicationContext, getString(R.string.toast_success), Toast.LENGTH_SHORT).show()
        }catch (ex:Exception){
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            Toast.makeText(applicationContext, getString(R.string.toast_fail), Toast.LENGTH_SHORT).show()
        }
    }

    private fun reset(view: View){
        textViewCarLoan.text = "$loanText"
        textViewInterest.text = "$intText"
        textViewMonthlyRepayment.text = "$monthlyText"

        editTextCarDownPayment.text.clear()
        editTextCarPrice.text.clear()
        editTextInterestRate.text.clear()
        editTextLoanPeriod.text.clear()

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        Toast.makeText(applicationContext, getString(R.string.toast_clear), Toast.LENGTH_SHORT).show()

        editTextCarPrice.requestFocus()
    }
}