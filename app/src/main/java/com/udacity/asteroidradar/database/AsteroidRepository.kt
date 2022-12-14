package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Asteroid

class AsteroidRepository(private val asteroidDao: AsteroidDao) {

    val readAllData: LiveData<List<Asteroid>> = asteroidDao.readAllData()

    suspend fun addAsteroid(asteroid: Asteroid){
        asteroidDao.addAsteroidData(asteroid)
    }

    suspend fun deleteAllAsteroid(){
        asteroidDao.deleteAllAsteroid()
    }

}