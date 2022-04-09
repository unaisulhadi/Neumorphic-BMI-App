package com.hadi.bmicalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.hadi.bmicalculator.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        init()
    }

    fun init(){

        val bmi = intent.getFloatExtra("BMI_VALUE",24.53F)
        detailBmiValue.text = bmi.toString()
        detailStatus.text = interpretBMI(bmi)


        back.setOnClickListener {
            finish()
        }
    }


    fun interpretBMI(bmiValue: Float): String {
        if (bmiValue < 16) {
            detailStatus.setTextColor(ContextCompat.getColor(this,R.color.severeUnderWeight))
            return "Severely Underweight"
        } else if (bmiValue < 18.5) {
            detailStatus.setTextColor(ContextCompat.getColor(this,R.color.underWeight))
            return "Underweight"
        } else if (bmiValue < 25) {
            detailStatus.setTextColor(ContextCompat.getColor(this,R.color.normalWeight))
            return "Normal"
        } else if (bmiValue < 30) {
            detailStatus.setTextColor(ContextCompat.getColor(this,R.color.overWeight))
            return "Overweight"
        } else if (bmiValue < 35) {
            detailStatus.setTextColor(ContextCompat.getColor(this,R.color.obesityClass1))
            return "Obesity Class I"
        } else if (bmiValue < 40) {
            detailStatus.setTextColor(ContextCompat.getColor(this,R.color.obesityClass2))
            return "Obesity Class II"
        }else{
            detailStatus.setTextColor(ContextCompat.getColor(this,R.color.obesityClass3))
            return "Obesity Class III"
        }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }
}
