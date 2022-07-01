public class WeatherModel {

    String weatherDescription;
    String temperature;
    String cityName;

    public WeatherModel(String weatherDescription, String temperature, String cityName) {
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.cityName = cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "weatherDescription='" + weatherDescription + '\'' +
                ", temperature='" + temperature + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
