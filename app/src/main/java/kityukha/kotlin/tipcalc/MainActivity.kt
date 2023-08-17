package kityukha.kotlin.tipcalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kityukha.kotlin.tipcalc.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcBtn.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDouble()
        val percentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_ten_percent -> 0.1
            R.id.option_seven_percent -> 0.07
            else -> {
                0.05
            }
        }
        var tip = cost * percentage
        val round = binding.roundSwitch.isChecked

        if (round) {
            tip = ceil(tip)
        }
        val currencyTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tvResult.text = getString(R.string.tip_amount, currencyTip)
    }
}