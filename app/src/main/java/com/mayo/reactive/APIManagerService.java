package com.mayo.reactive;


import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mahayogi Lakshmipathi on 25/10/16.
 *
 * @author <a href="mailto:mygreymatter@gmail.com">Mahayogi Lakshmipathi</a>
 * @version 1.0
 */

public interface APIManagerService {
    @GET("/weather")
    WeatherData getWeather(@Query("q") String place, @Query("units") String units);
}