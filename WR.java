import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {

  // API key for accessing the weather data
  private static final String API_KEY = "your_api_key_here";

  public static void main(String[] args) {
    // Get the city name from the user
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the city name: ");
    String city = reader.readLine();

    // Make a request to the OpenWeatherMap API to get the weather data for the city
    String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
    StringBuilder result = new StringBuilder();
    try {
      URL url = new URL(urlString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      rd.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Parse the JSON data and get the current temperature
    JSONObject json = new JSONObject(result.toString());
    JSONObject main = json.getJSONObject("main");
    double temp = main.getDouble("temp");
    System.out.println("Current temperature in " + city + ": " + temp + "°C");
  }
}
