public class City {

    String cityName;
    String countryCode;

    public City(String cityName, String countryCode) {
        this.cityName = cityName;
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
