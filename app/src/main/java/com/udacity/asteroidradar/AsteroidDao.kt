package com.udacity.asteroidradar

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAsteroidData(asteroid: Asteroid)

    @Query("DELETE FROM asteroid_data")
    suspend fun deleteAllAsteroid()

    @Query("SELECT * FROM asteroid_data ORDER BY id ASC")
    fun readAllData() : LiveData<List<Asteroid>>
}