import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame implements ActionListener {
    Container container = getContentPane();
    JButton enterCityButton = new JButton("Enter the city");
    JButton openCityInfo = new JButton("More about current location");
    JLabel temperatureLabel = new JLabel("Temperature in °C: ");
    JLabel weatherDescriptionLabel = new JLabel("Description: ");
    JLabel cityNameLabel = new JLabel("City: ");
    JTextField temperatureTextField = new JTextField();
    JTextField weatherDescriptionTextField = new JTextField();
    JTextField cityNameTextField = new JTextField();
    WeatherModel weatherModel;
    String url;


    public MainGUI (WeatherModel weatherModel, String url) {
        this.url = url;
        this.weatherModel = weatherModel;
        this.setTitle("City Informer App");
        this.setVisible(true);
        this.setBounds(10,10,500,450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setInfo();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setInfo() {
        weatherDescriptionTextField.setText(weatherModel.getWeatherDescription());
        temperatureTextField.setText(weatherModel.getTemperature());
        cityNameTextField.setText(weatherModel.getCityName());
    }

    public void setLocationAndSize() {
        enterCityButton.setBounds(50,300,120,30);
        openCityInfo.setBounds(200,300,200,30);
        temperatureLabel.setBounds(50,150,200,30);
        weatherDescriptionLabel.setBounds(50,200,100,30);
        cityNameLabel.setBounds(50,250,100,30);
        temperatureTextField.setBounds(200,150,150,30);
        weatherDescriptionTextField.setBounds(200,200,150,30);
        cityNameTextField.setBounds(200,250,150,30);
    }

    public void addComponentsToContainer() {
        container.add(enterCityButton);
        container.add(openCityInfo);
        container.add(temperatureLabel);
        container.add(weatherDescriptionLabel);
        container.add(cityNameLabel);
        container.add(temperatureTextField);
        container.add(weatherDescriptionTextField);
        container.add(cityNameTextField);
    }

    public void addActionEvent() {
        openCityInfo.addActionListener(this);
        enterCityButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enterCityButton) {
            NewCityGUI newCityGUI = new NewCityGUI();
            MainGUI.super.dispose();
        }

        if(e.getSource() == openCityInfo) {
            JFrame jFrame = new JFrame();
            jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jFrame.setVisible(true);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JFXPanel jfxPanel = new JFXPanel();
            jFrame.add(jfxPanel);
            Platform.runLater(() -> {
                WebView webView = new WebView();
                jfxPanel.setScene(new Scene(webView));
                webView.getEngine().load(url);
            });
        }
    }
}
