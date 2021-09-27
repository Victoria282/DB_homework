package ru.unit6.course.android.retrofit.data.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import ru.unit6.course.android.retrofit.R
import ru.unit6.course.android.retrofit.data.adapter.DatabaseAdapter
import ru.unit6.course.android.retrofit.data.model.UserDB
import ru.unit6.course.android.retrofit.data.view_model.DatabaseViewModel
import ru.unit6.course.android.retrofit.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home), DatabaseAdapter.PersonClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userViewModel: DatabaseViewModel
    private lateinit var adapter: DatabaseAdapter

    override fun onPersonClick(user_db: UserDB) {
        val action = HomeFragmentDirections.toDetails(user_db)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
        setupUI()
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            val action = HomeFragmentDirections.toAddPerson()
            findNavController().navigate(action)
        }
    }

    private fun setupUI() {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = DatabaseAdapter(arrayListOf())
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = adapter
        }
    }

    private fun setupObservers() {
        userViewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.addUsers(it)
            adapter.clickListener = this@HomeFragment
        }
    }
}