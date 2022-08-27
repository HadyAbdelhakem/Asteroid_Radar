package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val API_KEY = "WX3oyZytNMa2Hn1qtmnSZlhFq9J4JDeUKpcbcjqt"
    val START_DATE = getToDayDate()
    val END_DATE = getLastDayDate()
}

fun getToDayDate():String {
    val calendar = Calendar.getInstance()
    val currentTime = calendar.time
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    val today = dateFormat.format(currentTime)
    calendar.add (Calendar.DAY_OF_YEAR, 7)
    val lastTime = calendar.time
    val lastDay = dateFormat.format(lastTime)

    return today
}

fun getLastDayDate():String {
    val calendar = Calendar.getInstance()
    val currentTime = calendar.time
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    val today = dateFormat.format(currentTime)
    calendar.add (Calendar.DAY_OF_YEAR, 7)
    val lastTime = calendar.time
    val lastDay = dateFormat.format(lastTime)

    return lastDay
}