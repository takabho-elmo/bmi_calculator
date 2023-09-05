package com.example.bmicaliculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //get result with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }

    private fun validateInput(weight: String?, height: String?): Boolean {
        when {
            weight.isNullOrEmpty()&&height.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight and Height is empty", Toast.LENGTH_LONG).show()
                return false

            }
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false

            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float) {
        val resultIndex = findViewById<TextView>(R.id.tvindex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = getString(R.string.normal_range)

        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText = getString(R.string.underweight)
                color = R.color.under_weight
            }

            bmi in 18.50..24.99 -> {
                resultText = getString(R.string.normal)
                color = R.color.normal
            }

            bmi in 25.00..29.99 -> {
                resultText = getString(R.string.obese1)
                color = R.color.obese1
            }

            bmi in 30.00 .. 34.99 -> {
                resultText = getString(R.string.obese2)
                color = R.color.obese2
            }
            bmi in 30.00 .. 34.99 -> {
                resultText = getString(R.string.obese3)
                color = R.color.obese3
            }
            bmi >= 40.00 -> {
                resultText = getString(R.string.obese4)
                color = R.color.obese4
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText


    }
}