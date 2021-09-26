package ru.unit6.course.android.retrofit.data.repository

import androidx.lifecycle.LiveData
import ru.unit6.course.android.retrofit.data.database.UserDao
import ru.unit6.course.android.retrofit.data.model.UserDB

class DatabaseRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<UserDB>> = userDao.getAllUsers()

    suspend fun addUser(user_db:UserDB) {
        userDao.insertUser(user_db)
    }
     suspend fun getUser(id: Int) {
        userDao.getUser(id)
    }
}