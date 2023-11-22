# WeatherApp

The WeatherApp provides weather data based on the city.

# Architecture
Implemented the WeatherApp in MVVM architecture
The repository to get the weather data from API call is implement as a separate library so that it can be reused in any app. 
The library named "com.sangita:weather-library:1.1" is published to local machine and added as dependency in WeatherApp.And the details about the library is given below:

## Features

1. **getWeatherByLocationName:**
   Access this function to retrieve weather details by passing the city name.

2. **getWeatherByLatAndLon:**
   Access this function to retrieve weather details by passing latitude and longitude.

## Usage

### Integration

1. **Add Library Dependency:**
   Add the WeatherService library to your project's dependencies.
   ```groovy
   implementation 'com.example:weatherservice:1.0.0'

### dependency
1. add Internet Permission. Ensure your app's manifest includes the Internet permission.
2. Add android:usesCleartextTraffic="true" to the <application> element in your app's manifest file to allow clear text traffic.
   ```xml
   <application
    android:usesCleartextTraffic="true">
   </application>

### Security Considerations

It's crucial to **consider security implications** when allowing clear text traffic (`android:usesCleartextTraffic="true"`) in your app's manifest.

> **Note:** Allowing clear text traffic might expose sensitive data to potential security risks. Ensure your backend services **support secure HTTPS connections** to encrypt data transmitted between your app and the server.

**Sensitive data**, such as user credentials or personal information, should **never be transmitted over non-secure (HTTP) connections**. Always prioritize using HTTPS for secure communication.

Make sure to **audit and secure your backend APIs** to guarantee a secure communication channel between your app and the server.

For further security measures, consider implementing additional encryption and authentication mechanisms to safeguard user data.

## Examples
1. Getting Weather by Location Name - call the getWeatherByLocationName inside a coroutineScope/builder
   ```kotlin
   // Example to retrieve weather data by city name
   weatherRepository.getWeatherByLocationName("cityname",object:WeatherListener{
            override fun onWeatherDataReceived(weather: Weather) {
                //utilize weather
            }
            override fun onFailure(errorMessage: String) {
                //handle exception
            }
        })
2. Getting Weather by Latitude and Longitude - call the getWeatherByLatAndLon inside a coroutineScope/builder
   ```kotlin
   // Example to retrieve weather data by latitude and longitude
   weatherRepository.getWeatherByLatAndLon(latitude,longitude,object:WeatherListener{
            override fun onWeatherDataReceived(weather: Weather) {
                weatherData.value = weather
            }
            override fun onFailure(errorMessage: String) {
                Log.d(TAG, "Error data retrieval$errorMessage")
            }
        })

## License
Since I use the WeatherService library internally, I didn't add any License

Requirements
1. Create a native-app-based application to serve as a basic weather app. It is preferable to create a reusable library separate from the application.
2. Search Screen 
 a. Allow customers to enter a US city - Implemented - User can enter any city. Future implementation - I'd implement US city by utilizing the Google API to filter the city
 b. Call the openweathermap.org API and display the information you think a user would be interested in seeing. Be sure to has the app
download and display a weather icon. Implemented - App display weather of the user entered city in fahrenheit.
   c. Have image cache if needed - Future implementation - Cache image in internal storage.
3. Auto-load the last city searched upon app launch. Future implementation - Cache the last city in SharedPreferences
4. Ask the User for location access, If the User gives permission to access the location, then retrieve weather data by default. Future implementation - Ask user permission to access user current location 


