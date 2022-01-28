package edu.monmouth.cs250.s1164308.metriccalc

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import edu.monmouth.cs250.s1164308.metriccalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val metricCalculator:Calculator = Calculator()
    private lateinit var binding:ActivityMainBinding
    var list_of_units = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        for(unit in Units.values()) {
            list_of_units.add(unit.name)
        }
        binding.spinner.setOnItemSelectedListener(this)

        //create an ArrayAdapter to hold dropdown items
        val aa = ArrayAdapter(this, R.layout.simple_spinner_item, list_of_units)
        //set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //set Adapter to Spinner
        binding.spinner.setAdapter(aa)

        setContentView(binding.root)
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        //use position to know the selected item
        metricCalculator.setMode(Units.values()[position])
        binding.result.text = "----"
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    //Target-Action method for Button press event
    fun convertData(view: View) {
        if (binding.inputValue.text.isNotEmpty()){
            try {
                val inputValue = binding.inputValue.text.toString().toDouble()
                val metricValue = metricCalculator.convert(inputValue)
                binding.result.text = String.format("%.2f", metricValue)
            } catch (e: NumberFormatException) {
                binding.result.text = "Enter valid number"
            }
        }
    }
}