package com.example.weatherapp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherservice.Weather
import com.example.weatherservice.WeatherListener
import com.example.weatherservice.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class WeatherViewModel : ViewModel() {
    //WeatherViewModel expose weather in flow to observe
    private val temperature:String = ""
    private val weatherRepository = WeatherRepository()
    private val weatherData = MutableStateFlow(temperature)
    val uiWeatherState: StateFlow<String> = weatherData.asStateFlow()

    /*refreshWeatherData retrieves weather data from getWeatherByLocationName
    @param location as String
    @return void
     */

    fun refreshWeatherData(location:String) {
        viewModelScope.launch {
            weatherRepository.getWeatherByLocationName(location,object: WeatherListener {
                override fun onWeatherDataReceived(weather: Weather) {
                    weatherData.value = weather.temperature?.temp.run {->
                        ((this?.minus(273.15)?.times(1.8)?.plus(32) ?: 0) as Double).toInt().toString()
                    }
                }
                override fun onFailure(errorMessage: String) {
                    Log.d(TAG, "Error data retrieval $errorMessage")
                    weatherData.value = ("City $errorMessage")
                }
            })
        }

    }


}