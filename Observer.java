import java.util.ArrayList;
import java.util.List;


/*
  - The Observer pattern defines a one-to-many relationship between objects.
  - The Observer pattern allows multiple objects to listen and react to events or changes.
  - The Observer pattern is used when you need to maintain consistency between related objects.
 */

interface Subject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

interface IObserver {
    void update(String str);
}

class WeatherStation implements Subject {
    private String weather;
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(weather);
        }
    }

    public void setWeather(String newWeather) {
        this.weather = newWeather;
        notifyObservers();
    }
}

class PhoneDisplay implements IObserver {
    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    public void display() {
        System.out.println("Phone Display: " + weather);
    }
}

class TabletDisplay implements IObserver {
    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    public void display() {
        System.out.println("Tablet Display: " + weather);
    }
}

public class Observer {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        WeatherStation weatherStation = new WeatherStation();
        weatherStation.registerObserver(new PhoneDisplay());
        weatherStation.registerObserver(new TabletDisplay());
        weatherStation.setWeather("Sunny");
    }
}
