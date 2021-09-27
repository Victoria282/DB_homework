package ru.unit6.course.android.retrofit.data.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.unit6.course.android.retrofit.R
import ru.unit6.course.android.retrofit.data.view_model.DatabaseViewModel
import ru.unit6.course.android.retrofit.data.model.UserDB
import ru.unit6.course.android.retrofit.databinding.FragmentAddPersonBinding

class addPersonFragment : Fragment(R.layout.fragment_add_person) {

    private lateinit var binding: FragmentAddPersonBinding
    private lateinit var viewModel: DatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPersonBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
        binding.sendDataPerson.setOnClickListener {
            sendData()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun sendData() {
        val name = binding.editTextTextPersonName.text.toString()
        val email = binding.editTextTextPersonEmail.text.toString()
        val url = binding.editTextTextPersonUrl.text.toString()
        if(name.trim().isEmpty() || email.trim().isEmpty() || url.trim().isEmpty()) {
            Toast.makeText(context, "Введите данные!", Toast.LENGTH_LONG).show()
        }
        else {
            val userDB = UserDB(0, name, email, url)
            viewModel.addUser(userDB)
            Toast.makeText(context,"Успешно!", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        }
    }
}