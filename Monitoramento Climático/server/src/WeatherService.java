package server.src;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    private static final String API_KEY = "C54CY4GKBL2CA49XWP5Y354NG";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";


    public String getWeatherData(String location) throws IOException, InterruptedException {
        String url = BASE_URL + location + "?key=" + API_KEY;
        return sendRequest(url);
    }
    public String getWeatherHistory(String location, String date1, String date2) throws IOException, InterruptedException {
        String url = BASE_URL + location + "/" + date1 + "/" + date2 + "?key=" + API_KEY;
        return sendRequest(url);
    }
    private String sendRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

