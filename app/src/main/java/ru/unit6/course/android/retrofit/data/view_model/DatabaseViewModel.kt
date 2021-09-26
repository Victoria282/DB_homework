package ru.unit6.course.android.retrofit.data.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.unit6.course.android.retrofit.data.database.AppDatabase
import ru.unit6.course.android.retrofit.data.repository.DatabaseRepository
import ru.unit6.course.android.retrofit.data.model.UserDB

class DatabaseViewModel(application: Application):AndroidViewModel(application) {
     val readAllData: LiveData<List<UserDB>>
     private val databaseRepository: DatabaseRepository

    init {
        val userDao = AppDatabase.getDatabase().userDao()
        databaseRepository = DatabaseRepository(userDao)
        readAllData = databaseRepository.readAllData
    }
    fun addUser(user: UserDB) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.addUser(user)
        }
    }
     fun getUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getUser(id)
        }
    }
}