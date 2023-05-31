package com.example.unitconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculate() }

        val fromSpinner: Spinner = binding.fromSpinner
        val toSpinner: Spinner = binding.toSpinner

        loadSpinner(fromSpinner)
        loadSpinner(toSpinner)
//        fromSpinner.onItemSelectedListener = SpinnerActivity(toSpinner)
    }

    private fun calculate() {
        val stringInTextField = binding.input.text.toString()
        val inputDouble = stringInTextField.toDoubleOrNull()

        val from = binding.fromSpinner.selectedItem.toString()
        val to = binding.toSpinner.selectedItem.toString()

        when(Pair(from,to)) {
            Pair("cm","km") -> {
                val result = inputDouble?.div(100000)
                binding.resultText.text = result.toString()
            }
            Pair("cm","mts") -> {
                val result = inputDouble?.div(100)
                binding.resultText.text = result.toString()
            }
            else -> {
                binding.resultText.text = "0"
            }
        }
    }

    private fun loadSpinner(spinner: Spinner) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.distanceAndVolume,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }
}