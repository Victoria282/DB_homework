package ru.unit6.course.android.retrofit.data.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.unit6.course.android.retrofit.R
import ru.unit6.course.android.retrofit.data.model.UserDB
import ru.unit6.course.android.retrofit.data.view_model.DatabaseViewModel
import ru.unit6.course.android.retrofit.databinding.DetailsFragmentBinding
import ru.unit6.course.android.retrofit.utils.Status

class DetailsFragment() : Fragment(R.layout.details_fragment) {
    private lateinit var binding: DetailsFragmentBinding
    private lateinit var viewModel: DatabaseViewModel

    private val args: DetailsFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        binding.text.text = "Уникальный идентификатор: " + args.personId
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
        binding.toHomeFragment.setOnClickListener {
            val action = DetailsFragmentDirections.toHome()
            findNavController().navigate(action)
        }
        setupUI()
    }


    private fun setupUI() {

    }

}