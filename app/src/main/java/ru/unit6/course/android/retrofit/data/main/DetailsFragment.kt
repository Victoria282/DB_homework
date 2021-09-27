package ru.unit6.course.android.retrofit.data.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.details_fragment.view.*
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
        binding.text.text = "Здравствуйте,  " + args.user.name + "!"
        setHasOptionsMenu(true)
        with(binding) {
            editTextTextPersonName.setText(args.user.name)
            editTextTextPersonEmail.setText(args.user.email)
            editTextTextPersonUrl.setText(args.user.avatar)
            Glide.with(avatar.context)
                .load(args.user.avatar)
                .into(binding.avatar)

            changeDataPerson.setOnClickListener {
                updateCurrentUser()
            }
            deleteBtn.setOnClickListener {
                DeleteUser()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
    }


    private fun updateCurrentUser() {
        val name = binding.editTextTextPersonName.text.toString()
        val email = binding.editTextTextPersonEmail.text.toString()
        val url = binding.editTextTextPersonUrl.text.toString()
        if(name.trim().isEmpty() || email.trim().isEmpty() || url.trim().isEmpty()) {
            Toast.makeText(context, "Введите данные!", Toast.LENGTH_LONG).show()
        }
        else {
            val updateUser = UserDB(args.user.id, name, email, url)
            viewModel.updateUser(updateUser)
            Toast.makeText(context, "Успешно!", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        }
    }

    private fun DeleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Удалить") { _,_->
            viewModel.deleteUser(args.user)
            Toast.makeText(context, "Пользователь удален!", Toast.LENGTH_LONG).show()
            toHome()
        }
        builder.setNegativeButton("Отмена") { _,_-> }
        builder.setTitle("Удалить пользователя ${args.user.name} ?")
        builder.create().show()
    }
    private fun toHome() {
        findNavController().navigateUp()
    }
}