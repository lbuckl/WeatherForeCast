package com.gb.Weather.view.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.Weather.databinding.FragmentContactsListItemBinding
import com.gb.Weather.databinding.FragmentWeatherListItemBinding
import com.gb.Weather.domain.Weather

class ContactsAdapter(private val contactList: List<String>):
    RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>(){

    //Создаёт ViewHolder объект опираясь на их количество, но с запасом, чтобы можно было скролить
    //Возвращает наш объект класса ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = FragmentContactsListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(binding.root)
    }

    //Связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    //Возвращает количество элементов списка
    override fun getItemCount(): Int {
        return contactList.size
    }

    //Вложенный класс для отображения данных в fragment_weather_list_item.xml
    inner class ContactViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(contactname:String){
            val binding = FragmentContactsListItemBinding.bind(itemView)
            binding.contactItem.text = contactname
        }
    }
}