package com.jgonzalez.contdordecopos

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {

    val nome = "jhoel"
    val numero = 15
    val buttonPlus: Button by lazy {findViewById(R.id.buttonPlusLabel)}
    val buttonLess: Button by lazy {findViewById(R.id.buttonLessLabel)}
    val counterGlassesOutput: TextView by lazy {findViewById(R.id.counterGlassesOutput)}
    var cupCounter: Int = 0

    val sharedPreferences by lazy { getSharedPreferences("my_prefs", MODE_PRIVATE) }
    val editor by lazy { sharedPreferences.edit() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cupCounter = sharedPreferences.getInt("cupCounter", 0)
        counterGlassesOutput.text = getString(R.string.cup_counter, cupCounter.toString())

        setupView()
    }


    private fun setupView() {

        buttonPlus.setOnClickListener {
            cupCounter++
            updateCounter()
        }

        buttonLess.setOnClickListener {
            if (cupCounter == 0) {
                updateCounter()
            } else {
                cupCounter--
                updateCounter()
            }

        }
    }

    private fun updateCounter() {
        counterGlassesOutput.text = getString(R.string.cup_counter, cupCounter.toString())

        editor.putInt("cupCounter", cupCounter)
        editor.apply()
    }

}
