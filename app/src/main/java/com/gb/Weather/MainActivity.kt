package com.gb.Weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gb.Weather.databinding.ActivityMainBinding
import com.gb.Weather.view.weatherlist.WeatherListFragment


internal class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.myRoot)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container,WeatherListFragment.newInstance())
                .addToBackStack("")
                .commit()
        }
    }
}
