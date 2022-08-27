package com.udacity.asteroidradar

import androidx.lifecycle.LiveData

class AsteroidRepository(private val asteroidDao: AsteroidDao) {

    val readAllData: LiveData<List<Asteroid>> = asteroidDao.readAllData()

    suspend fun addAsteroid(asteroid: Asteroid){
        asteroidDao.addAsteroidData(asteroid)
    }

    suspend fun deleteAllAsteroid(){
        asteroidDao.deleteAllAsteroid()
    }

}