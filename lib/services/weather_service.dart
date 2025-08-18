import 'dart:convert';
import 'package:http/http.dart' as http;

class WeatherService {
  static const String apiKey = "6cea24f3bf81bf835dfaf7d04887055a";

  static Future<Map<String, dynamic>> getWeather(double lat, double lon) async {
   final String url = "https://api.openweathermap.org/data/2.5/weather"
    "?lat=$lat&lon=$lon&appid=6cea24f3bf81bf835dfaf7d04887055a&units=metric";

    final response = await http.get(Uri.parse(url));

    if (response.statusCode == 200) {
      final data = jsonDecode(response.body);

      return {
        "temp": data["main"]["temp"],
        "condition": data["weather"][0]["main"],
        "description": data["weather"][0]["description"],
      };
    } else {
      throw Exception("Failed to load weather: ${response.body}");
    }
  }
}
