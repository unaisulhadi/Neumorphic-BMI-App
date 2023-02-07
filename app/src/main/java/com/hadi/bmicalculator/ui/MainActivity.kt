package com.hadi.bmicalculator.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.hadi.bmicalculator.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var gender = "Male"
    var height = 175
    var weight = 75
    var age = 25



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        setContentView(R.layout.activity_main)

        init()

    }

    fun init() {

//        if(delegate.localNightMode == AppCompatDelegate.MODE_NIGHT_YES){
//            light_mode.setImageResource(R.drawable.ic_light)
//        }else{
//            light_mode.setImageResource(R.drawable.ic_moon_dark)
//        }


        maleCard.setOnClickListener {
            male.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this,
                    R.color.colorSecondary
                ))
            female.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this,
                    R.color.colorPrimary
                ))


            maleText.setTextColor(ContextCompat.getColor(this,R.color.lightColor))
            maleIcon.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.lightColor
            ))

            femaleText.setTextColor(ContextCompat.getColor(this,R.color.black))
            femaleIcon.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            ))


            gender = "Male"

            //Toast.makeText(this, gender, Toast.LENGTH_SHORT).show()
        }

        femaleCard.setOnClickListener {
            male.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this,
                    R.color.colorPrimary
                ))

            maleText.setTextColor(ContextCompat.getColor(this,R.color.black))
            maleIcon.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.black
            ))

            femaleText.setTextColor(ContextCompat.getColor(this,R.color.lightColor))
            femaleIcon.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this,
                R.color.lightColor
            ))


            female.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this,
                    R.color.colorSecondary
                ))


            gender = "Female"

            //Toast.makeText(this, gender, Toast.LENGTH_SHORT).show()
        }

        valueWeight.setOnClickListener {
            valueWeight.isCursorVisible = true
        }

        valueAge.setOnClickListener {
            valueAge.isCursorVisible = true
        }

        valueWeight.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                valueWeight.isCursorVisible = false
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    valueWeight.isCursorVisible = false

                    if (valueWeight.text.toString().isEmpty() || valueWeight.text.toString().toInt() == 0)  {
                        weight = 75
                        valueWeight.setText(weight.toString())
                    } else {
                        weight = valueWeight.text.toString().toInt()
                    }
                }

                return false
            }
        })

        valueAge.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                valueAge.isCursorVisible = false
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    valueAge.isCursorVisible = false


                    if (valueAge.text.toString().isEmpty() || valueAge.text.toString().toInt() == 0) {
                        age = 25
                        valueAge.setText(age.toString())
                    } else {
                        age = valueAge.text.toString().toInt()
                    }
                }

                return false
            }
        })

        plusWeight.setOnClickListener {
            if (weight < 999) {
                weight += 1
                valueWeight.setText(weight.toString())
            }
        }

        minusWeight.setOnClickListener {

            if (weight > 1) {
                weight -= 1
                valueWeight.setText(weight.toString())
            }
        }

        plusAge.setOnClickListener {
            if (age < 999) {
                age += 1
                valueAge.setText(age.toString())
            }
        }

        minusAge.setOnClickListener {
            if (age > 1) {
                age -= 1
                valueAge.setText(age.toString())
            }
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                height = progress
                valueHeight.text = "$progress cm"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        calculateBmi.setOnClickListener {

            when {
                TextUtils.isEmpty(valueWeight.text.toString()) -> {
                    Toast.makeText(this,"Please enter correct Weight",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(valueAge.text.toString()) -> {
                    Toast.makeText(this,"Please enter correct Age",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    age = valueAge.text.toString().toInt()
                    weight = valueWeight.text.toString().toInt()

                    var bmi = bmi_calc(height.toFloat(),weight.toFloat()).toString() // replace from stringformat

                    //Toast.makeText(this,"$height  $weight  $age $bmi",Toast.LENGTH_SHORT).show()

                    val intent =Intent(this,ResultsActivity::class.java)
                    intent.putExtra("BMI_VALUE",bmi)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }


        }

//        light_mode.setOnClickListener {
//
//            if(delegate.localNightMode == AppCompatDelegate.MODE_NIGHT_YES){
//                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }else{
//                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//        }
    }

    fun bmi_calc(height: Float, weight: Float): Float {

        var heightInMeter = height/100

        var bmi_Val = weight / (heightInMeter * heightInMeter)
        return bmi_Val
    }




}
