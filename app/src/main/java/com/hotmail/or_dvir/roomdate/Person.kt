package com.hotmail.or_dvir.roomdate

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "people")
class Person(
    @PrimaryKey var id: Long,
    val firstName: String,
    val lastName: String,
    val date: Calendar
) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}