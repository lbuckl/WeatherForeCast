package com.gb.Weather.view.menu

import android.Manifest
import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.net.Uri
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gb.Weather.MyApp
import com.gb.Weather.databinding.FragmentContactsListItemBinding
import com.gb.Weather.databinding.FragmentWeatherListBinding
import com.gb.Weather.domain.ContactNum
import com.gb.Weather.shared.showSnackBarErrorMsg

class ContactsAdapter(private val contactList: List<ContactNum>):
    RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>(){


    lateinit var binding: FragmentContactsListItemBinding


    //Создаёт ViewHolder объект опираясь на их количество, но с запасом, чтобы можно было скролить
    //Возвращает наш объект класса ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        binding = FragmentContactsListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(binding.root)
    }

    //Связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])

        holder.itemView.setOnClickListener{
            //Проверяем есть ли разрешение на звонки
            val permResult =
                ContextCompat.checkSelfPermission(MyApp.getMyApp().baseContext, Manifest.permission.CALL_PHONE)
            Log.d("@@@",permResult.toString())

            if (permResult == PackageManager.PERMISSION_GRANTED){
                Log.d("@@@","Пытаюсь позвонить")
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.setData(Uri.parse("tel:" + contactList[position].num)).addFlags(FLAG_ACTIVITY_NEW_TASK)
                MyApp.getMyApp().applicationContext.startActivity(callIntent)
            }
            else {
                binding.root.rootView.showSnackBarErrorMsg(
                    "Необходимо зайти в настройки и дать приложению разрешение на вызовы")
            }
        }
    }

    //Возвращает количество элементов списка
    override fun getItemCount(): Int {
        return contactList.size
    }

    //Вложенный класс для отображения данных в fragment_weather_list_item.xml
    inner class ContactViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(contactname:ContactNum){
            val binding = FragmentContactsListItemBinding.bind(itemView)
            binding.contactItem.text = contactname.name
            binding.textNumber.text = contactname.num
        }
    }
}