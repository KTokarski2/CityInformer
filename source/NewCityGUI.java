import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewCityGUI extends JFrame implements ActionListener, FileReader{

    Container container = getContentPane();
    JLabel cityNameLabel = new JLabel("City Name: ");
    JLabel countryCodeLabel = new JLabel("Country code: ");
    JTextField cityNameTextField = new JTextField();
    JTextField countryCodeTextField = new JTextField();
    JButton proceedButton = new JButton("Proceed");
    JButton alreadyKnownButton = new JButton("My cities");
    City city;
    List<City> cityList = new ArrayList<>();



    public NewCityGUI () {
        this.setTitle("Enter a new city");
        this.setVisible(true);
        this.setBounds(10,10,370,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        cityNameLabel.setBounds(50,30,100,30);
        countryCodeLabel.setBounds(50,100,100,30);
        cityNameTextField.setBounds(150,30,150,30);
        countryCodeTextField.setBounds(150,100,150,30);
        proceedButton.setBounds(80,180,90,30);
        alreadyKnownButton.setBounds(180,180,90,30);

    }

    public void addComponentsToContainer() {
        container.add(cityNameLabel);
        container.add(countryCodeLabel);
        container.add(cityNameTextField);
        container.add(countryCodeTextField);
        container.add(proceedButton);
        container.add(alreadyKnownButton);
    }

    public void addActionEvent() {
        proceedButton.addActionListener(this);
        alreadyKnownButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == proceedButton) {
            City city = new City(cityNameTextField.getText(), countryCodeTextField.getText());
            cityList.add(city);
            CitiesFileSaver fileSaver = new CitiesFileSaver(city);
            fileSaver.saveToFile();
            APIService service = new APIService();
            WeatherModel weatherModel = service.createWeatherModel(service.getWeatherJSON(service.createWeatherURL(city)));
            String url = "https://en.wikipedia.org/wiki/" + weatherModel.getCityName();
            MainGUI mainGUI = new MainGUI(weatherModel,url);
            NewCityGUI.super.dispose();
        }

        if(e.getSource() == alreadyKnownButton) {
            List<City> cities = readFile();
            EnteredCitiesGUI gui = new EnteredCitiesGUI(cities);
            NewCityGUI.super.dispose();
        }
    }

    public City getCity() {
        return city;
    }
}
