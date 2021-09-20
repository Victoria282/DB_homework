package ru.unit6.course.android.retrofit.data.repository

import retrofit2.Response
import ru.unit6.course.android.retrofit.data.api.ApiHelper
import ru.unit6.course.android.retrofit.data.api.RetrofitBuilder
import ru.unit6.course.android.retrofit.data.model.User

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

    suspend fun getUser(personId: String) = apiHelper.getUser(personId)

    suspend fun sendData(post: User): Response<User> {
        return RetrofitBuilder.apiService.sendData(post)
    }
}