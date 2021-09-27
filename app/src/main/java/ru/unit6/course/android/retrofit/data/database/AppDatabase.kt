package ru.unit6.course.android.retrofit.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.unit6.course.android.retrofit.data.model.UserDB

// version текущей ДБ
@Database(entities = [UserDB::class],version = 1, exportSchema = false)
// Аннотацией Database помечаем основной класс по работе с базой данных.
// Этот класс должен быть абстрактным и наследовать RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    // В Database классе необходимо описать абстрактные методы для получения Dao объектов,
    // которые понадобятся (запросы которые будем выполнять)
    abstract fun userDao() : UserDao

    companion object {
        // null если база данных еще не создана
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "MyDatabase"

        // при создании DB необходим контекст прил-я (БД создается)
        fun invoke(context: Context) : AppDatabase{
            val temp = INSTANCE
            if(temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return INSTANCE!!
            }
        }
    }
}