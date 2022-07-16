package com.gb.Weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.Weather.databinding.FragmentLoadingBinding
import com.gb.Weather.databinding.FragmentWeatherListBinding

class LoadingFragment: Fragment() {

    lateinit var binding_load: FragmentLoadingBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        //Биндинг для прямой связи View
        binding_load = FragmentLoadingBinding.inflate(inflater)
        return binding_load.root
    }
}