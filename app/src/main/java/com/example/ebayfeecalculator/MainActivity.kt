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
        var itemCost: EditText = findViewById(R.id.itemCost)
        var sellPrice: EditText = findViewById(R.id.sellingPrice)
        var buyerShipping: EditText = findViewById(R.id.shippingBuyer)
        var shippingCost: EditText = findViewById(R.id.shippingCost)

        //Calculate button
        val calculateButton: Button = findViewById(R.id.calculate_button)

        //Reset button
        val resetButton: Button = findViewById(R.id.reset_button)

        //TODO replace toast message with a Textview showing results for expected profit


        //Calculates profit and/or ebay fees when user hits submit button//
        calculateButton.setOnClickListener{
            var itemCostInt: Double = itemCost.text.toString().toDouble()
            var sellPriceInt: Double = sellPrice.text.toString().toDouble()
            var buyerShippingInt: Double = buyerShipping.text.toString().toDouble()
            var shippingCostInt: Double = shippingCost.text.toString().toDouble()



            val ebayFees = ((sellPriceInt + buyerShippingInt) * (FINAL_FEE_VARIOUS) + FEE_PER_ORDER).toString()
            val estimate = BigDecimal((sellPriceInt + buyerShippingInt - itemCostInt - shippingCostInt - ebayFees.toDouble()).toString()).setScale(2, RoundingMode.HALF_EVEN)


            Toast.makeText(this@MainActivity, "The expected profit will be $estimate", Toast.LENGTH_LONG).show()




        }
        //resets all values to "" once button is clicked
        resetButton.setOnClickListener{
            itemCost.setText("")
            sellPrice.setText("")
            buyerShipping.setText("")
            shippingCost.setText("")


        }
    }


    }

