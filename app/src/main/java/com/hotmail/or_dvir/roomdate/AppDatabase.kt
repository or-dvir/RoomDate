package com.hotmail.or_dvir.roomdate

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@Database(entities = [Person::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}

class Converters {
    @TypeConverter
    fun fromTimeMillis(millis: Long): Calendar {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis

        return cal
    }

    @TypeConverter
    fun toTimeMillis(cal: Calendar): Long {
        return cal.timeInMillis
    }
}