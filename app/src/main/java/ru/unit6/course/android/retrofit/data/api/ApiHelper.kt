package ru.unit6.course.android.retrofit.data.api

import ru.unit6.course.android.retrofit.data.model.User

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()

    suspend fun getUser(personId: String) = apiService.getUser(personId)

    suspend fun sendData(post: User) = apiService.sendData(post)
}