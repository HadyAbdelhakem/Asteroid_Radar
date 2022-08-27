package com.udacity.asteroidradar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Asteroid::class] , version = 1 , exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract fun asteroidDao() : AsteroidDao

    companion object{
        @Volatile
        private var INSTANCE: AsteroidDatabase? = null

        fun getDatabase(context : Context) : AsteroidDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AsteroidDatabase::class.java,
                    "asteroid_data"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}