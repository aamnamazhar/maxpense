import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:geolocator/geolocator.dart';
import 'package:maxpense/controllers/auth_controller.dart';
import 'package:maxpense/services/location_service.dart';
import 'package:maxpense/services/weather_service.dart';
import 'package:maxpense/screens/notes_screen.dart';

class DashboardScreen extends StatefulWidget {
  const DashboardScreen({super.key});

  @override
  State<DashboardScreen> createState() => _DashboardScreenState();
}

class _DashboardScreenState extends State<DashboardScreen> {
  String? address;
  String? temperature;
  String? weatherCondition;

  @override
  void initState() {
    super.initState();
    _loadLocationAndWeather();
  }

  Future<void> _loadLocationAndWeather() async {
  try {
    Position position = await LocationService.getCurrentLocation();
    String addr = await LocationService.getAddressFromLatLng(position);

    Map<String, dynamic> weather = await WeatherService.getWeather(
      position.latitude,
      position.longitude,
    );

    setState(() {
      address = addr;
      temperature = weather["temp"]?.toString();
      weatherCondition = weather["condition"];
    });
    debugPrint("📍 Address: $address, 🌡️ Temp: $temperature, ☁️ Condition: $weatherCondition");
  } catch (e) {
    setState(() {
      address = "Error fetching location";
      temperature = null;
      weatherCondition = null;
    });
    debugPrint("❌ Error loading location/weather: $e");
  }
}

  Widget _glossyCard({required Widget child}) {
    return Container(
      decoration: BoxDecoration(
        boxShadow: [
          BoxShadow(
            color: Colors.black.withOpacity(0.15),
            blurRadius: 12,
            offset: const Offset(0, 6),
          ),
        ],
        borderRadius: BorderRadius.circular(20),
      ),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(20),
        child: BackdropFilter(
          filter: ImageFilter.blur(sigmaX: 10, sigmaY: 10),
          child: Container(
            decoration: BoxDecoration(
              gradient: LinearGradient(
                colors: [
                  const Color(0xFF575DFB).withOpacity(0.6),
                  const Color(0xFF575DFB).withOpacity(0.3),
                ],
                begin: Alignment.topLeft,
                end: Alignment.bottomRight,
              ),
              borderRadius: BorderRadius.circular(20),
              border: Border.all(color: Colors.white.withOpacity(0.2), width: 1),
            ),
            padding: const EdgeInsets.all(20),
            child: child,
          ),
        ),
      ),
    );
  }

  /// Weather Icon
  IconData _getWeatherIcon(String? condition) {
    if (condition == null) return Icons.wb_cloudy;

    final lower = condition.toLowerCase();
    if (lower.contains("clear")) return Icons.wb_sunny;
    if (lower.contains("rain")) return Icons.grain;
    if (lower.contains("cloud")) return Icons.cloud;
    if (lower.contains("snow")) return Icons.ac_unit;
    if (lower.contains("storm") || lower.contains("thunder")) return Icons.flash_on;
    return Icons.wb_cloudy;
  }

  @override
  Widget build(BuildContext context) {
    final user = FirebaseAuth.instance.currentUser;

    return Scaffold(
  backgroundColor: const Color(0xFFF5F6FA),
  body: SafeArea(
    child: Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              IconButton(
                icon: const Icon(Icons.arrow_back_ios, size: 20, color: Color(0xFF575DFB)),
                onPressed: () => Navigator.pop(context),
              ),
              IconButton(
                icon: const Icon(Icons.power_settings_new_rounded, size: 24, color: Color(0xFF575DFB)),
                onPressed: () async {
                  await AuthController.logout();
                  if (context.mounted) Navigator.pop(context);
                },
              ),
            ],
          ),

          const SizedBox(height: 20),

          /// Welcome Card
          _glossyCard(
            child: Row(
              children: [
                const CircleAvatar(
                  radius: 28,
                  backgroundColor: Color.fromARGB(186, 255, 255, 255),
                  child: Icon(Icons.person,
                      size: 30, color: Color.fromARGB(171, 87, 93, 251)),
                ),
                const SizedBox(width: 16),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      "Welcome back 👋,",
                      style: TextStyle(fontSize: 18, color: Colors.white),
                    ),
                    const SizedBox(height: 6),
                    Text(
                      user?.displayName ?? "No Name",
                      style: const TextStyle(
                        fontSize: 22,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),

          const SizedBox(height: 20),

          /// Weather Card
          _glossyCard(
            child: Row(
              children: [
                Icon(
                  _getWeatherIcon(weatherCondition),
                  color: Colors.white,
                  size: 40,
                ),
                const SizedBox(width: 16),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        "Todays Weather",
                        style: const TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                      const SizedBox(height: 8),
                      Text(
                        address ?? "Current Location...",
                        style: const TextStyle(fontSize: 16, color: Colors.white),
                      ),
                      const SizedBox(height: 10),
                      Text(
                        (temperature != null && weatherCondition != null)
                            ? "$temperature°C, $weatherCondition"
                            : "Loading weather...",
                        style: const TextStyle(
                          fontSize: 16,
                          color: Colors.white,
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
          const Spacer(),
        ],
      ),
    ),
  ),
  floatingActionButton: FloatingActionButton.extended(
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(14),
    ),
    elevation: 6,
    backgroundColor: Colors.white,
    icon: const Icon(Icons.add_comment_rounded, color: Color(0xFF575DFB)),
    label: const Text(
      "Notes",
      style: TextStyle(
        color: Color(0xFF575DFB),
        fontWeight: FontWeight.w600,
      ),
    ),
    onPressed: () => Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const NotesScreen()),
    ),
  ),
  floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
);

  }
}
