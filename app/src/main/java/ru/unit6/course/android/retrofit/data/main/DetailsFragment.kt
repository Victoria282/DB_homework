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
import com.bumptech.glide.Glide
import org.chromium.base.Log
import ru.unit6.course.android.retrofit.R
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
        // назад на список
        binding.toHomeFragment.setOnClickListener {
            val action = DetailsFragmentDirections.toHome()
            findNavController().navigate(action)
        }
        binding.sendDataBtn.setOnClickListener {
            // Создаем пользователя для отправки
            // id ставится автоматически
            val myPost = User("https://pbs.twimg.com/media/E6WweHAWUAE4VV0.jpg", "vilkova_viktoria2000@mail.ru", "", "Victoria")
            viewModel.sendData(myPost)
            viewModel.myResponse.observe(viewLifecycleOwner) { response ->
                val message: String
                if(response.isSuccessful) {
                    message =  "Тело запроса:" + response.body().toString() +
                               "\n Код отправки: " + response.code().toString() +
                               "\n Статус отправки:" + response.message().toString()
                    // Подтверждение на экран
                    Toast.makeText(context, "Успешно!", Toast.LENGTH_SHORT).show()
                    // Результат отправки в консоль
                    println("\nResult post request:\n$message")
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
        val userInfo = arrayListOf<User>()
        // val userTest : User ?= null
        viewModel.getUser(args.personId).observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    resource.data?.let {
                        user: User ->  userInfo.add(user)
                        // user: User ->  userTest
                    }
                    // Эта часть кода не работает, userTest всегда null
                   /* if(userTest != null) {
                        binding.name.text = userTest.name
                        binding.email.text = userTest.email
                        Glide.with(binding.avatar.context)
                            .load(userTest.avatar)
                            .into(binding.avatar)
                    }*/
                    binding.name.text = userInfo[0].name
                    binding.email.text = userInfo[0].email
                    Glide.with(binding.avatar.context)
                        .load(userInfo[0].avatar)
                        .into(binding.avatar)
                }
                Status.ERROR -> {
                    Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}