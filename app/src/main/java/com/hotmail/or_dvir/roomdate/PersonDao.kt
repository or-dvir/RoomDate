package com.hotmail.or_dvir.roomdate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface PersonDao {

    @Query("SELECT * FROM people WHERE date BETWEEN :from AND :to")
    suspend fun getAllByDate(from: Calendar,to: Calendar): List<Person>

    @Query("SELECT * FROM people")
    suspend fun getAll(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg people: Person)
}