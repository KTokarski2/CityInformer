import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CitiesFileSaver {

    private City city;

    public CitiesFileSaver(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void saveToFile() {
        try {
            FileWriter fileWriter = new FileWriter("ProgramFiles/AlreadyEnteredCities.txt",true);
            String fileString = city.cityName + "," + city.countryCode;
            fileWriter.write(fileString + "\r\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
