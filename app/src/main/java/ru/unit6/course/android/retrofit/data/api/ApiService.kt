package ru.unit6.course.android.retrofit.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import ru.unit6.course.android.retrofit.data.model.User

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User

    @POST("users")
    suspend fun sendData(@Body post: User): Response<User>
}