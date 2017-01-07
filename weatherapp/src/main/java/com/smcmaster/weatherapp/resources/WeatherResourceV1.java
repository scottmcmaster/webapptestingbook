package com.smcmaster.weatherapp.resources;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smcmaster.weatherapp.models.Weather;
import com.smcmaster.weatherapp.openweathermap.OpenWeatherMap;

@Path("/weatherv1")
public class WeatherResourceV1 {

  private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

  private ObjectMapper mapper = new ObjectMapper();
  
  @SuppressWarnings("unchecked")
  @GET
  @Produces("application/json")
  @Path("/{country}/{city}")
  public Weather getWeatherForCity(@PathParam("country") String country,
      @PathParam("city") String city) throws Exception {
    // Should not be receiving null path parameters.
    assert city != null;
    assert country != null;

    String urlStr = BASE_URL + "q=" + URLEncoder.encode(city, "utf-8") + ","
        + URLEncoder.encode(country, "utf-8") + "&units=metric&appid="
        + OpenWeatherMap.getApiKey();
    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    int statusCode = conn.getResponseCode();
    if (statusCode != 200) {
      throw new NotFoundException();
    }

    String result = IOUtils.toString((InputStream) conn.getContent(), Charset.defaultCharset());

    // Turn JSON result into a Weather resource...
    Map<String, Object> map = new HashMap<>();
    map = mapper.readValue(result, new TypeReference<Map<String, Object>>(){});

    Weather weather = new Weather();    
    weather.setCityName(city);
    if (map.containsKey("main")) {
      Map<String, Object> mainData = (Map<String, Object>) map.get("main");
      if (mainData.containsKey("temp")) {
        weather.setTemperature(mainData.get("temp").toString());
      }
      if (mainData.containsKey("pressure")) {
        weather.setPressure(mainData.get("pressure").toString());
      }
      if (mainData.containsKey("humidity")) {
        weather.setHumidity(mainData.get("humidity").toString());
      }
    }
    
    if (map.containsKey("wind")) {
      Map<String, Object> windData = (Map<String, Object>) map.get("wind");
      if (windData.containsKey("speed")) {
        String windStr = windData.get("speed").toString();
        if (windData.containsKey("deg")) {
          windStr += "@";
          windStr += windData.get("deg").toString();
        }
        weather.setWind(windStr);
      }
    }
    return weather;
  }
}
