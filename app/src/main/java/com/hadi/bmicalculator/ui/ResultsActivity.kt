package com.hadi.bmicalculator.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hadi.bmicalculator.R
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {

    var bmiValue = 0F
    lateinit var progressAnimator: ObjectAnimator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        init()
    }

    private fun init(){

        var bmi_ = intent.getStringExtra("BMI_VALUE")
        bmiValue = bmi_.toFloat()

        //Toast.makeText(this,"$bmiValue",Toast.LENGTH_SHORT).show()


        back.setOnClickListener {
            finish()
        }



        progress.max = 25
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progress.setProgress(bmiValue.toInt(),true)
        }else{
            progress.setProgress(bmiValue.toInt())
        }

//        progressAnimator = ObjectAnimator.ofInt(progress,"progress",bmiValue.toInt())
//        progressAnimator.duration = 1000
//        progressAnimator.setInterpolator(DecelerateInterpolator());
//        progressAnimator.start()


        tvBmiValue.text = bmiValue.toString()

        tvBmiStatus.text = interpretBMI(bmiValue)


        showDetails.setOnClickListener {
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("BMI_VALUE",bmiValue)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

    }

    fun interpretBMI(bmiValue: Float): String {
        if (bmiValue < 16) {
            progress.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.severeUnderWeight))
            return "You are Severely Underweight"
        } else if (bmiValue < 18.5) {
            progress.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.underWeight))
            return "You are Underweight"
        } else if (bmiValue < 25) {
            progress.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.normalWeight))
            return "You have Normal Body Weight"
        } else if (bmiValue < 30) {
            progress.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.md_amber_600))
            return "You are Overweight"
        } else if (bmiValue < 35) {
            progress.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.obesityClass1))
            return "You have Obesity Class I"
        } else if (bmiValue < 40) {
            progress.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.obesityClass2))
            return "You have Obesity Class II"
        }else{
            progress.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.obesityClass3))
            return "You have Obesity Class III"
        }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }


}
