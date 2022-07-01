import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EnteredCitiesGUI extends JFrame implements ActionListener {

    Container container = getContentPane();
    JButton acceptButton = new JButton("Accept");
    JButton backButton = new JButton("Back");
    List<City> cities = new ArrayList<>();
    City city = new City("Wypizgarz", "doopa");
    JComboBox citiesComboBox;

    public EnteredCitiesGUI (List<City> list) {

        citiesComboBox = new JComboBox(list.toArray());
        this.setTitle("Choose your city");
        this.setVisible(true);
        this.setBounds(10,10,420,450);
        this.setResizable(false);
        setLayoutManager();
        addComponentsToContainer();
        setLocationAndSize();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        citiesComboBox.setBounds(50, 30, 300, 30);
        acceptButton.setBounds(230, 370, 100, 30);
        backButton.setBounds(80,370,100,30);
    }

    public void addComponentsToContainer() {
        container.add(acceptButton);
        container.add(backButton);
        container.add(citiesComboBox);
    }

    public void addActionEvent() {
        acceptButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            EnteredCitiesGUI.super.dispose();
        }

        if(e.getSource() == acceptButton) {
            City city = (City) citiesComboBox.getSelectedItem();
            APIService service = new APIService();
            WeatherModel weatherModel = service.createWeatherModel(service.getWeatherJSON(service.createWeatherURL(city)));
            String url = "https://en.wikipedia.org/wiki/" + weatherModel.getCityName();
            MainGUI mainGUI = new MainGUI(weatherModel,url);
            EnteredCitiesGUI.super.dispose();

        }
    }
}
