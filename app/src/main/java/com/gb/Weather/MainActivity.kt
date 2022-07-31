package com.gb.Weather

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.gb.Weather.databinding.ActivityMainBinding
import com.gb.Weather.view.menu.WeatherHistoryFragment
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
                //.addToBackStack("")
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_history -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .replace(R.id.container, WeatherHistoryFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
