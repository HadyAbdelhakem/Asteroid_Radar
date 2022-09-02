package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.*
import com.udacity.asteroidradar.api.AsteroidFilter
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.PictureOfDayApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.AsteroidRepository
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _picResponse = MutableLiveData<PictureOfDay>()
    val readAllData: LiveData<List<Asteroid>>
    private val repository: AsteroidRepository

    private val _asteroidStatus = MutableLiveData<String>()

    val picResponse: LiveData<PictureOfDay>
        get() = _picResponse

    val asteroidStatus: LiveData<String>
        get() = _asteroidStatus

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid?>()
    val navigateToSelectedAsteroid: LiveData<Asteroid?>
        get() = _navigateToSelectedAsteroid

    init {
        val asteroidDao = AsteroidDatabase.getDatabase(application).asteroidDao()
        repository = AsteroidRepository(asteroidDao)
        readAllData = repository.readAllData
        coroutineScope.launch {
            getPictureOfDayObject()
            getNasaApiData(AsteroidFilter.VIEW_WEEK)
        }
    }

    private suspend fun getNasaApiData(end_date: AsteroidFilter) {
        withContext(Dispatchers.IO) {
            try {
                var jsonResult = NasaApi.retrofitService.getAsteroid(
                    Constants.START_DATE,
                    end_date.value,
                    Constants.API_KEY
                )
                val resultArrayList = parseAsteroidsJsonResult(JSONObject(jsonResult))
                addNewAsteroid(resultArrayList)
                Log.i("Connected_Success", "Successful Added")
            } catch (e: Exception) {
                Log.i("Can_not_connected", e.message.toString())
            }
        }
    }


    private fun getPictureOfDayObject() {
        PictureOfDayApi.picRetrofitService.getPictureOfDay(Constants.API_KEY)
            .enqueue(object : Callback<PictureOfDay> {
                override fun onResponse(
                    call: Call<PictureOfDay>,
                    response: Response<PictureOfDay>
                ) {
                    _picResponse.value = response.body()
                }

                override fun onFailure(call: Call<PictureOfDay>, t: Throwable) {
                    Log.i("Error: ", t.message + "")
                }

            })
    }

    private fun addNewAsteroid(asteroid: List<Asteroid>) {
        viewModelScope.launch {
            for (element in asteroid) {
                repository.addAsteroid(element)
                if (element.isHazadous){
                    _asteroidStatus.value = "The asteroid is hazardous"
                }
                else{
                    _asteroidStatus.value = "The asteroid is safe"
                }
            }
        }
    }

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

    fun updateFilter(end_date: AsteroidFilter) {
        coroutineScope.launch {
            if (end_date.value == Constants.START_DATE) {
                repository.deleteAllAsteroid()
            }
            getNasaApiData(end_date)
        }

    }
}