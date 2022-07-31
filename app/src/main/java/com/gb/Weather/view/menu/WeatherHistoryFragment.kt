package com.gb.Weather.view.menu

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentHistoryListBinding
import com.gb.Weather.viewmodel.HistoryAppState
import com.gb.Weather.viewmodel.HistoryViewModel

class WeatherHistoryFragment : Fragment() {

    companion object {
        lateinit var viewModel_history: HistoryViewModel
        //fun newInstance() = WeatherListFragment()
    }
    private var _binding_history: FragmentHistoryListBinding? = null
    private val binding_history: FragmentHistoryListBinding
        get() {
            return _binding_history!!
        }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        //Биндинг для прямой связи View
        _binding_history = FragmentHistoryListBinding.inflate(inflater)

        //инициализируем работу с меню (для скрытия кнопок)
        setHasOptionsMenu(true)
        return binding_history.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Получаем ссылку на наш экземпляр ViewModel
        viewModel_history = ViewModelProvider(this).get(HistoryViewModel::class.java)
        //запускаем подписку на AppState
        viewModel_history.getLiveData().observe(viewLifecycleOwner
        ) { t -> renderData(t) }
        viewModel_history.getHistory()
        binding_history.buttonDelete.setOnClickListener{
            viewModel_history.clearHistory()
        }
    }

    //Подписка на изменение AppState и выполнение операций по триггеру
    private fun renderData(appState: HistoryAppState){
        when (appState){
            is HistoryAppState.LoadedHistory-> {
                binding_history.historyRecyclerview.adapter = WeatherHistoryAdapter(appState.weatherList)
            }
        }
    }

    //скрываем опцию меню "история"
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.request_history)?.isVisible = false
    }
    

    override fun onDestroy() {
        super.onDestroy()
        _binding_history = null
    }
    //endregion
}