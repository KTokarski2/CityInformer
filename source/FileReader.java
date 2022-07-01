import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface FileReader {

    default List<City> readFile() {

        List<String> lines = new ArrayList<>();
        String [] line;
        List<City> cities = new ArrayList<>();
        try{
            lines = new BufferedReader(new java.io.FileReader("ProgramFiles/AlreadyEnteredCities.txt"))
                    .lines()
                    .collect(Collectors.toList());
            for (int i = 0; i < lines.size(); i++) {
                line = lines.get(i).split(",");
                City city = new City(line[0], line[1]);
                cities.add(city);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return cities;
    }

}
