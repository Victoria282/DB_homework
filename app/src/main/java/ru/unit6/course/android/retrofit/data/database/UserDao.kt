package ru.unit6.course.android.retrofit.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.unit6.course.android.retrofit.data.model.UserDB
import java.sql.RowId

// В объекте Dao мы будем описывать методы для работы с базой данных.
// Нам нужны будут методы для получения списка юзеров и для добавления/изменения/
// удаления юзеров.
//Описываем их в интерфейсе с аннотацией Dao.
@Dao
interface UserDao {
    // Add
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserDB)
    // Read
    @Query("SELECT * FROM users")
    fun getAllUsers():LiveData<List<UserDB>>

    @Query("Select * From users Where 'id' = :id")
    suspend fun getUser(id:Int) :UserDB
    /*


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<UserDB>)

    @Update
    suspend fun updateUser(user: UserDB)

    @Delete
    suspend fun deleteUser(user: UserDB)

    */
}