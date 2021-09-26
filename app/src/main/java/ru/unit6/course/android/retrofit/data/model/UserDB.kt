package ru.unit6.course.android.retrofit.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Класс помечается аннотацией Entity. Объекты класса UserDB будут использоваться при
// работе с базой данных. Например, мы будем получать их от базы при запросах данных и
// отправлять их в базу при вставке данных. Этот же класс UserDB будет использован для
// создания таблицы в базе. В качестве имени таблицы будет использовано имя класса.
// А поля таблицы будут созданы в соответствии с полями класса.

//Аннотацией PrimaryKey мы помечаем поле, которое будет ключом в таблице.
@Entity(tableName = "users")
data class UserDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="name")
    val name:String,
    @ColumnInfo(name = "email")
    val email:String,
    @ColumnInfo(name = "avatar")
    val avatar:String
)