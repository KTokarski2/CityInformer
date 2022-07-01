import com.google.gson.Gson;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class APIService {

    double kelvinToCelcius = 273.00;
    String connectionError = "Connection Error!";

    public String createWeatherURL(City city) {
        String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=" + city.getCityName() + "," + city.getCountryCode() + "&appid=4e2069d59e9edb32ab2a81f80467cc46";
        System.out.println(weatherURL);
        return weatherURL;
    }

    public String getWeatherJSON(String weatherURL) {
        String weatherJSON = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new URL(weatherURL).openConnection().getInputStream()
                    )
            );
            weatherJSON = bufferedReader.lines().collect(Collectors.joining());
            System.out.println(weatherJSON);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There was a problem with connection!", connectionError,JOptionPane.ERROR_MESSAGE);
        }
        return weatherJSON;
    }

    public WeatherModel createWeatherModel(String weatherJSON) {
        Gson gson = new Gson();
        DTO dto = gson.fromJson(weatherJSON,DTO.class);
        String weatherDesc = dto.weather.get(0).description;
        double temperature = dto.main.temp;
        String temperatureString = convert(temperature);
        String cityName = dto.name;
        return new WeatherModel(weatherDesc,temperatureString,cityName);
    }

    public String convert(double temperature) {
        double Celciustemperature = temperature - kelvinToCelcius;
        String temperatureString = String.format("%.1f",Celciustemperature);
        return temperatureString;
    }
}
