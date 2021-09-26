package ru.unit6.course.android.retrofit.data.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.unit6.course.android.retrofit.data.view_model.DatabaseViewModel
import ru.unit6.course.android.retrofit.data.model.UserDB
import ru.unit6.course.android.retrofit.databinding.FragmentAddPersonBinding

class addPersonFragment : Fragment() {

    private lateinit var binding: FragmentAddPersonBinding
    private lateinit var viewModel: DatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendDataPerson.setOnClickListener {
            sendData()
        }
    }

    private fun sendData() {
        val name = binding.editTextTextPersonName.text.toString()
        val email = binding.editTextTextPersonEmail.text.toString()
        val url = binding.editTextTextPersonUrl.text.toString()
        val userDB = UserDB(0, name, email, url)
        Toast.makeText(context,"Успешно!", Toast.LENGTH_LONG).show()
        viewModel.addUser(userDB)
        toHome()
    }

    private fun toHome() {
        val action = addPersonFragmentDirections.toHome()
        findNavController().navigate(action)
    }
}