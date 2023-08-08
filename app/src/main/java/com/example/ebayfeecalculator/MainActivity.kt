package com.example.ebayfeecalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

const val FINAL_FEE_VARIOUS = .1325
const val FEE_PER_ORDER = .30

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create Spinner object to hold categories
        val categories = resources.getStringArray(R.array.categories_array)
        val spinner: Spinner = findViewById(R.id.category_spinner)
        if (spinner != null){
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
            spinner.adapter = adapter
        }

        //TODO: Implement Spinner select listener to do a different formula based on selection
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                /*Toast.makeText(
                    this@MainActivity,
                    getString(R.string.shipping_cost) + " " +
                            "" + categories[position], Toast.LENGTH_SHORT
                ).show() */
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
       /*ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        */


        //Filling in values based on user input for item cost, sell price, buyer shipping, shipping cost
        val itemCost: EditText = findViewById(R.id.itemCost)
        val sellPrice: EditText = findViewById(R.id.sellingPrice)
        val buyerShipping: EditText = findViewById(R.id.shippingBuyer)
        val shippingCost: EditText = findViewById(R.id.shippingCost)

        //Calculate button
        val calculateButton: Button = findViewById(R.id.calculate_button)

        //TODO replace toast message with a Textview showing results for expected profit
        var resultText: TextView;


        //Calculates profit and/or ebay fees when user hits submit button//
        calculateButton.setOnClickListener{
            val itemCostInt: Double = itemCost.text.toString().toDouble()
            val sellPriceInt: Double = sellPrice.text.toString().toDouble()
            val buyerShippingInt: Double = buyerShipping.text.toString().toDouble()
            val shippingCostInt: Double = shippingCost.text.toString().toDouble()



            val ebayFees = ((sellPriceInt + buyerShippingInt) * (FINAL_FEE_VARIOUS) + FEE_PER_ORDER).toString()
            val estimate = BigDecimal((sellPriceInt + buyerShippingInt - itemCostInt - shippingCostInt - ebayFees.toDouble()).toString()).setScale(2, RoundingMode.HALF_EVEN)

            Toast.makeText(this@MainActivity, "The expected profit will be $estimate", Toast.LENGTH_LONG).show()




        }
    }


    }

