package com.example.ebayfeecalculator

import android.content.ContentValues.TAG
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import java.math.BigDecimal
import java.math.RoundingMode

const val FINAL_FEE_VARIOUS = .1325
const val FEE_PER_ORDER = .30
const val FINAL_FEE_BOOKS = .1495
const val FINAL_FEE_COINS = .1325
const val FINAL_FEE_CLOTHES = .15
const val FINAL_FEE_JEWELRY = .15
const val FINAL_FEE_ART = .05
const val FINAL_FEE_INDUSTRY_EQUIP = .03
const val LIST_FEE_INDUSTRY_EQUIP = 20
const val FINAL_FEE_GUITAR = .0635
const val FINAL_FEE_MEN_SHOES_OVER150 = .08
const val FINAL_FEE_MEN_SHOES_UNDER150 = .1325
const val ONE_HUNDRED_FIFTY= 150




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        //This call sets the screen orientation to be locked in portrait even if user rotates
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //create Spinner object to hold categories
        val categories = resources.getStringArray(R.array.categories_array)
        val spinner: Spinner = findViewById(R.id.category_spinner)
        if (spinner != null){
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
            spinner.adapter = adapter
        }


        //Filling in values based on user input for item cost, sell price, buyer shipping, shipping cost
        val itemCost: EditText = findViewById(R.id.itemCost)
        val sellPrice: EditText = findViewById(R.id.sellingPrice)
        val buyerShipping: EditText = findViewById(R.id.shippingBuyer)
        val shippingCost: EditText = findViewById(R.id.shippingCost)


        //Calculate button
        val calculateButton: Button = findViewById(R.id.calculate_button)

        //Reset button
        val resetButton: Button = findViewById(R.id.reset_button)

        var ebayEstimate: TextView = findViewById(R.id.profitText)
        var ebayFees: TextView = findViewById(R.id.ebayFees)

        var finalValueFee: Double = 0.0;


        //TODO replace toast message with a Textview showing results for expected profit





        //TODO: Implement Spinner select listener to do a different formula based on selection
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long) {
                if(position ==1){
                    finalValueFee = FINAL_FEE_BOOKS
                } else if(position == 2){
                    finalValueFee = FINAL_FEE_COINS
                }else if(position ==3){
                    finalValueFee = FINAL_FEE_CLOTHES
                } else if(position ==4){
                    finalValueFee = FINAL_FEE_JEWELRY
                } else if(position ==5) {
                    finalValueFee = FINAL_FEE_INDUSTRY_EQUIP
                }else if(position ==6) {
                    finalValueFee = FINAL_FEE_ART
                } else if(position ==7){
                    finalValueFee = FINAL_FEE_GUITAR
                } else if(position ==8 && sellPrice.text.toString().toDouble() <= ONE_HUNDRED_FIFTY){
                    finalValueFee = FINAL_FEE_MEN_SHOES_UNDER150
                } else if(position ==9 && sellPrice.text.toString().toDouble() >= ONE_HUNDRED_FIFTY)
                    finalValueFee = FINAL_FEE_MEN_SHOES_OVER150
                else {
                    finalValueFee  = FINAL_FEE_VARIOUS
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        //Calculates profit and/or ebay fees when user hits submit button//
        calculateButton.setOnClickListener{


            if(itemCost.text.isEmpty()){
                itemCost.setText("0")
                Snackbar.make(calculateButton, "Auto-filled with 0 due to blank input", Snackbar.LENGTH_SHORT)
                    .show()
            }
            if(sellPrice.text.isEmpty()){
                sellPrice.setText("0")
                Snackbar.make(calculateButton, "Auto-filled with 0 due to blank input", Snackbar.LENGTH_SHORT)
                    .show()
            }
            if(buyerShipping.text.isEmpty()){
                buyerShipping.setText("0")
                Snackbar.make(calculateButton, "Auto-filled with 0 due to blank input", Snackbar.LENGTH_SHORT)
                    .show()
            }
            if(shippingCost.text.isEmpty()){
                shippingCost.setText("0")
                Snackbar.make(calculateButton, "Auto-filled with 0 due to blank input", Snackbar.LENGTH_SHORT)
                    .show()
                }

            var itemCostInt: Double = itemCost.text.toString().toDouble()
            var sellPriceInt: Double = sellPrice.text.toString().toDouble()
            var buyerShippingInt: Double = buyerShipping.text.toString().toDouble()
            var shippingCostInt: Double = shippingCost.text.toString().toDouble()

            val ebayFees = ((sellPriceInt + buyerShippingInt) * (finalValueFee) + FEE_PER_ORDER).toString()
            val estimate = BigDecimal((sellPriceInt + buyerShippingInt - itemCostInt - shippingCostInt - ebayFees.toDouble()).toString()).setScale(2, RoundingMode.HALF_EVEN)


        

            ebayEstimate.text = ("Profit Estimate "+ "$ " + (BigDecimal((sellPriceInt + buyerShippingInt - itemCostInt - shippingCostInt - ebayFees.toDouble()).toString()).setScale(2, RoundingMode.HALF_EVEN)))
            //Toast.makeText(this@MainActivity, "The expected profit will be $$estimate", Toast.LENGTH_LONG).show()




        }
       // ebayFees.setText((sellPrice + buyerShipping) * (finalValueFee) + FEE_PER_ORDER).toString()

        //resets all values to "" once button is clicked
        resetButton.setOnClickListener{
            itemCost.setText("")
            sellPrice.setText("")
            buyerShipping.setText("")
            shippingCost.setText("")


        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onDestroy() called")
    }


    }

