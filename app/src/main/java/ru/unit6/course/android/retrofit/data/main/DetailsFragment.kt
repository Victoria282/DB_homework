package ru.unit6.course.android.retrofit.data.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chromium.base.Log
import ru.unit6.course.android.retrofit.R
import ru.unit6.course.android.retrofit.data.api.ApiService
import ru.unit6.course.android.retrofit.data.api.RetrofitBuilder.apiService
import ru.unit6.course.android.retrofit.data.model.User
import ru.unit6.course.android.retrofit.data.view_model.MainViewModel
import ru.unit6.course.android.retrofit.databinding.DetailsFragmentBinding
import ru.unit6.course.android.retrofit.utils.Status

class DetailsFragment() : Fragment(R.layout.details_fragment) {
    private lateinit var binding: DetailsFragmentBinding
    private lateinit var viewModel: MainViewModel

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n", "ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toHomeFragment.setOnClickListener {
            val action = DetailsFragmentDirections.toHome()
            findNavController().navigate(action)
        }
        binding.sendDataBtn.setOnClickListener {
            val myPost = User("https://goo.su/7rUa", "vilkova_viktoria2000@mail.ru", "", "Виктория"
        )
        viewModel.sendData(myPost)
        viewModel.myResponse.observe(viewLifecycleOwner) { response ->
            val message: String
                if(response.isSuccessful) {
                    message = "Тело запроса:" + response.body() +
                            "\nКод отправки: " + response.code() +
                            "\nСтатус отправки:" + response.message()
                    Toast.makeText(context, "Успешно!", Toast.LENGTH_SHORT).show()
                    println("\nРезультат Post запроса:\n$message")
                }
                else {
                    Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.text.text = "Уникальный идентификатор: " + args.personId
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupObservers()
    }
    private fun setupObservers() {
        viewModel.getUser(args.personId).observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    resource.data?.let {
                        setUserInfo(it)
                    }
                }
                Status.ERROR -> { Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show() }

                Status.LOADING -> { binding.progressBar.visibility = View.VISIBLE }
            }
        }
    }
    private fun setUserInfo(user: User) {
        binding.name.text = user.name
        binding.email.text = user.email
        Glide.with(binding.avatar.context)
              .load(user.avatar)
              .into(binding.avatar)
    }

}