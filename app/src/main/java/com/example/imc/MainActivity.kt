package com.example.imc


import android.content.Intent
import android.health.connect.datatypes.HeightRecord
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.imc.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentHeigth: Int = 120
    private var currentWeigth: Int = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()

        binding.tvHeight.text=binding.slHeight.value.toString()


    }

    private fun initListeners(){
        binding.cvMale.setOnClickListener { changeGenderColor("male") }
        binding.cvFemale.setOnClickListener { changeGenderColor("female") }
        binding.slHeight.addOnChangeListener { _ , value, _ -> changeHeight(value) }
        binding.btnPlusWigth.setOnClickListener { changeWeight("+") }
        binding.btnPlusYears.setOnClickListener { changeYear("+") }
        binding.btnLessWigth.setOnClickListener { changeWeight("-") }
        binding.btnLessYears.setOnClickListener { changeYear("-") }
        binding.btnCalculate.setOnClickListener { calculateIMC() }
    }

    private fun changeGenderColor(gender:String) {
        if(gender == "male"){
            Toast.makeText(this,"male",Toast.LENGTH_LONG).show()
            binding.cvMale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_component_selected))
            binding.cvFemale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_component))

        }
        else if(gender == "female"){
            Toast.makeText(this,"female",Toast.LENGTH_LONG).show()
            binding.cvFemale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_component_selected))
            binding.cvMale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_component))
        }
    }

    private fun changeHeight(value: Float) {
        val df = DecimalFormat("#.##")
        currentHeigth = df.format(value).toInt()
        binding.tvHeight.text = "$currentHeigth cm"
    }

    private fun changeYear(operator: String) {
        var valueString = binding.tvYears.text.toString()
        var valueInt = valueString.toInt()
        if (operator == "+"){
            valueInt++
        }else{
            valueInt--
        }
        binding.tvYears.text = valueInt.toString()
    }

    private fun changeWeight(operator: String) {
        var valueString = binding.tvWeight.text.toString()
        currentWeigth = valueString.toInt()
        if (operator == "+"){
            currentWeigth++
        }else{
            currentWeigth--
        }
        binding.tvWeight.text = currentWeigth.toString()
    }

    private fun calculateIMC() {
        val HeightMeters = currentHeigth.toFloat()/100
        var IMC = currentWeigth/((HeightMeters*HeightMeters))
        goToResult(IMC)
    }

    private fun goToResult(IMC : Float) {
        val mensaje = "$IMC"
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("mensaje", mensaje)
        }
        startActivity(intent)
    }


}