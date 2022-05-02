package com.neo.composenoteapp.util

import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.*

class DateConverter {

    @TypeConverter
    fun timeStampFromDate(date: Date): Long =
        date.time

    @TypeConverter
    fun dateFromTimeStamp(timestamp: Long): Date =
        Date(timestamp)
}