package com.udacity.asteroidradar.test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.AsteroidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AsteroidViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Asteroid>>
    private val repository: AsteroidRepository

    init {
        val asteroidDao = AsteroidDatabase.getDatabase(application).asteroidDao()
        repository = AsteroidRepository(asteroidDao)
        readAllData = repository.readAllData
    }

    fun addAsteroid(asteroid: Asteroid){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAsteroid(asteroid)
        }
    }

    fun deleteAllAsteroid(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllAsteroid()
        }
    }

}