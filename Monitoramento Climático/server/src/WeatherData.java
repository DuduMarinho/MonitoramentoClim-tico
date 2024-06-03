package server.src;
public class WeatherData {
    private String location;
    private String temperature;
    private String condition;

    public WeatherData(String location, String temperature, String condition) {
        this.location = location;
        this.temperature = temperature;
        this.condition = condition;
    }


//getters e setters

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
}
