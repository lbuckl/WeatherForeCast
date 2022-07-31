package com.gb.Weather.view.menu

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.gb.Weather.databinding.FragmentContactsListBinding
import com.gb.Weather.domain.ContactNum
import com.gb.Weather.shared.showSnackBarErrorMsg

class ContactsFragment: Fragment() {
    private var _binding: FragmentContactsListBinding? = null
    private val binding: FragmentContactsListBinding
        get() {
            return _binding!!
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    //Функция проверяет, получено ли разрешение на
    private fun checkPermission() {
        /**
         * ContextCompat.checkSelfPermission
         * Возвращает:PackageManager.PERMISSION_GRANTED,
         * если у вас есть разрешение, или PackageManager.PERMISSION_DENIED, если нет.
         */
        val permContacts =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)
        if (permContacts == PackageManager.PERMISSION_GRANTED) {
            getContacts()
            //Проверяем а не 2 ли это попытка запросить разрешение
        } else if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
            AlertDialog.Builder(requireContext())
                .setTitle("Доступ к контактам и звонкам")
                .setMessage("Для того, чтобы работать с контактами и делать вызовы нужно иметь разрешение на доступ к ним")
                .setPositiveButton("Логично") { _, _ ->
                    permissionRequest(
                        arrayOf(Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CALL_PHONE))
                }
                .setNegativeButton("Бред") { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        } else {
            view?.showSnackBarErrorMsg("Необходимо зайти в настройки и дать приложению разрешение на вызовы")
            permissionRequest(arrayOf(Manifest.permission.READ_CONTACTS,
                Manifest.permission.CALL_PHONE))
        }
    }

    private fun permissionRequest(permissions: Array<String>) {
        requestPermissions(permissions, REQUEST_CODE_READ_CONTACTS)
    }

    private val REQUEST_CODE_READ_CONTACTS = 999

    //Функиця в которую ложатся все результаты запросов Permissions
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_READ_CONTACTS) {
            //Проходимся по всем разрешениям и ищем разрешен ли нужный нам
            for (pIndex in permissions.indices) {
                if (permissions[pIndex] == Manifest.permission.READ_CONTACTS
                    && grantResults[pIndex] == PackageManager.PERMISSION_GRANTED
                ) {
                    getContacts()
                    Log.d("@@@", "Ура")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    //Функция для запроса контактов
    @SuppressLint("Range")
    private fun getContacts() {
        val contentResolver: ContentResolver = requireContext().contentResolver

        //Определяем столбцы для запроса
        val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            ContactsContract.CommonDataKinds.Phone.NUMBER)


        // Отправляем запрос на получение контактов и получаем ответ в виде Cursor
        val cursorWithContacts: Cursor? = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            queryFields,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        )



        val contacts = mutableListOf<ContactNum>()

        cursorWithContacts?.let { cursor->
            for(i in 1 until cursor.count){ // аналог 0..cursorWithContacts.count-1
                cursor.moveToPosition(i)
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNum:String

                phoneNum = if (cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER) > 0){
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                } else {""}
                contacts.add(ContactNum(name,phoneNum))
            }
        }

        cursorWithContacts?.close()
        binding.reciclerContacts.adapter = ContactsAdapter(contacts)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}