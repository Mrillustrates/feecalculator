package com.example.ebayfeecalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val spinner: Spinner = findViewById(R.id.category_spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val itemCost: EditText = findViewById(R.id.itemCost)

        val sellPrice: EditText = findViewById(R.id.sellingPrice)

        val buyerShipping: EditText = findViewById(R.id.shippingBuyer)

        val shippingCost: EditText = findViewById(R.id.shippingCost)

        val calculateButton: Button = findViewById(R.id.calculate_button)

        calculateButton.setOnClickListener{
            val itemCostInt: Double = itemCost.text.toString().toDouble()
            val sellPriceInt: Double = sellPrice.text.toString().toDouble()
            val buyerShippingInt: Double = buyerShipping.text.toString().toDouble()
            val shippingCostInt: Double = shippingCost.text.toString().toDouble()


            val estimate = ((sellPriceInt + buyerShippingInt) - (itemCostInt + shippingCostInt) * (.15 + .30)).toString()
            Toast.makeText(this@MainActivity, "The expected profit will be $estimate", Toast.LENGTH_LONG).show()




        }
    }


    }
