package com.hotmail.or_dvir.roomdate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
                .fallbackToDestructiveMigration()
                .build()

        val jan1st2020Start = Calendar.getInstance().apply {
            set(2020, 0, 1, 0, 0, 0)
        }

        val jan1st2019Start = Calendar.getInstance().apply {
            set(2019, 0, 1, 0, 0, 0)
        }

        val person2020 = Person(1, "john", "smith", jan1st2020Start)
        val person2019 = Person(2, "jenny", "mack", jan1st2019Start)

        CoroutineScope(Dispatchers.Main).launch {
            database.personDao().insertAll(person2020, person2019)

            //get everyone
            val everyone = database.personDao().getAll().joinToString()
            Log.i("aaaaa", "everyone: $everyone")

            //get only people from january 1st 2019
            val jan1st2019End = Calendar.getInstance().apply {
                set(2019, 0, 1, 23, 59, 59)
            }
            val allFromJan1st2019 =
                database.personDao().getAllByDate(jan1st2019Start, jan1st2019End)
            Log.i("aaaaa", "2019: ${allFromJan1st2019.joinToString()}")
        }
    }
}