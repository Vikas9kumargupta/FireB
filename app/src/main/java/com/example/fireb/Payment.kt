package com.example.fireb

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fireb.databinding.ActivityPaymentBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import java.lang.Exception
import java.time.temporal.TemporalAmount

class Payment : AppCompatActivity(), PaymentResultListener {

    lateinit var txtPaymentStatus : TextView
    lateinit var pay : EditText
    lateinit var btnPayNow : Button
    private lateinit var binding: ActivityPaymentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtPaymentStatus = binding.paymentStatus

        binding.btnPayNow.setOnClickListener {
            savePayment(binding.pay.text.toString().trim().toInt())
        }

        Checkout.preload(this@Payment)

    }

    private fun savePayment(amount: Int){
        val checkOut = Checkout()
        try {
            val options = JSONObject()
            options.put("name","SankatMochak")
            options.put("description","A Small amount of help will Help other a Lot")
            options.put("image","R.drawable.help")
            options.put("theme.color","#3399cc")
            options.put("currency","INR")
            options.put("amount",amount*100)

            val retryObj = JSONObject()
            retryObj.put("enabled",true)
            retryObj.put("max_count",4)
            options.put("retry",retryObj)

            checkOut.open(this@Payment,options)
        } catch (e: Exception){
            Toast.makeText(this@Payment,"Error in Payment : " + e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        txtPaymentStatus.text = "Payment Successful!!"
        txtPaymentStatus.setTextColor(Color.GREEN)
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        txtPaymentStatus.text = "Payment Failed!!"
        txtPaymentStatus.setTextColor(Color.RED)
    }
}