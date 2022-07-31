package com.gb.Weather.view.menu

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.gb.Weather.R
import com.gb.Weather.databinding.FragmentHistoryListBinding

class ContactsFragment: Fragment() {
    private var _binding_contacts: FragmentHistoryListBinding? = null
    private val binding_contacts: FragmentHistoryListBinding
        get() {
            return _binding_contacts!!
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        //инициализируем работу с меню (для скрытия кнопок)
        setHasOptionsMenu(true)
        return binding_contacts.root
    }

    //скрываем опцию меню "история"
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.menu_item_contacts)?.isVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding_contacts = null
    }
}