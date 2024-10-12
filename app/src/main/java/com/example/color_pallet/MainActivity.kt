package com.example.color_pallet

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // UI components
    private lateinit var colorView: View
    private lateinit var switchRed: SwitchCompat
    private lateinit var switchGreen: SwitchCompat
    private lateinit var switchBlue: SwitchCompat
    private lateinit var seekBarRed: SeekBar
    private lateinit var seekBarGreen: SeekBar
    private lateinit var seekBarBlue: SeekBar
    private lateinit var editTextRed: EditText
    private lateinit var editTextGreen: EditText
    private lateinit var editTextBlue: EditText
    private lateinit var resetButton: Button

    // RGB values
    private var red = 255
    private var green = 255
    private var blue = 255

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize components
        colorView = findViewById(R.id.colorView)
        switchRed = findViewById(R.id.switch1)
        switchGreen = findViewById(R.id.switch2)
        switchBlue = findViewById(R.id.switch3)
        seekBarRed = findViewById(R.id.seekBar4)
        seekBarGreen = findViewById(R.id.seekBar5)
        seekBarBlue = findViewById(R.id.seekBar6)
        editTextRed = findViewById(R.id.editTextRed)
        editTextGreen = findViewById(R.id.editTextGreen)
        editTextBlue = findViewById(R.id.editTextBlue)
        resetButton = findViewById(R.id.button)

        // Initialize default values and listeners
        setupListeners()
        resetValues()  // Set default to white and turn switches on by default
    }

    private fun setupListeners() {
        // Switch for enabling/disabling Red control
        switchRed.setOnCheckedChangeListener { _, isChecked ->
            seekBarRed.isEnabled = isChecked
            if (!isChecked) {
                // Reset red value to 0 when switch is turned off
                red = 0
                seekBarRed.progress = 0
                editTextRed.setText("0.00")
            }
            updateColor()
        }

        // Switch for enabling/disabling Green control
        switchGreen.setOnCheckedChangeListener { _, isChecked ->
            seekBarGreen.isEnabled = isChecked
            if (!isChecked) {
                // Reset green value to 0 when switch is turned off
                green = 0
                seekBarGreen.progress = 0
                editTextGreen.setText("0.00")
            }
            updateColor()
        }

        // Switch for enabling/disabling Blue control
        switchBlue.setOnCheckedChangeListener { _, isChecked ->
            seekBarBlue.isEnabled = isChecked
            if (!isChecked) {
                // Reset blue value to 0 when switch is turned off
                blue = 0
                seekBarBlue.progress = 0
                editTextBlue.setText("0.00")
            }
            updateColor()
        }

        // SeekBar listener for Red
        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                red = progress
                val redScaled = red / 255.0f
                editTextRed.setText(String.format(Locale.US, "%.2f", redScaled))
                updateColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // SeekBar listener for Green
        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                green = progress
                val greenScaled = green / 255.0f
                editTextGreen.setText(String.format(Locale.US, "%.2f", greenScaled))
                updateColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // SeekBar listener for Blue
        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blue = progress
                val blueScaled = blue / 255.0f
                editTextBlue.setText(String.format(Locale.US, "%.2f", blueScaled))
                updateColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Reset button listener to reset RGB values and UI
        resetButton.setOnClickListener {
            resetValues()
        }
    }

    // Method to update the color based on the current RGB values
    private fun updateColor() {
        val color = Color.rgb(red, green, blue)

        // Set the color of the view
        colorView.setBackgroundColor(color)
    }

    // Method to reset all values to white and switches to "on"
    private fun resetValues() {
        // Set SeekBars to 255 (white) and EditTexts to "1.00"
        seekBarRed.progress = 255
        seekBarGreen.progress = 255
        seekBarBlue.progress = 255
        editTextRed.setText("1")
        editTextGreen.setText("1")
        editTextBlue.setText("1")

        // Set the background color to white (RGB: 255, 255, 255)
        colorView.setBackgroundColor(Color.WHITE)

        // Turn all switches on by default
        switchRed.isChecked = true
        switchGreen.isChecked = true
        switchBlue.isChecked = true

        // Enable the seekbars after resetting
        seekBarRed.isEnabled = true
        seekBarGreen.isEnabled = true
        seekBarBlue.isEnabled = true
    }
}