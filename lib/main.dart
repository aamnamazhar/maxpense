import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:maxpense/screens/dashboard_screen.dart';
import 'package:maxpense/screens/home_screen.dart';
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp( MyApp()
  );
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Max Pense',
      debugShowCheckedModeBanner: false,
      theme: ThemeData.light(),
      home: AuthWrapper(),
    );
  }
}

class AuthWrapper extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return StreamBuilder<User?>
    (stream: FirebaseAuth.instance.authStateChanges(), 
    builder: (context, snapshot){
      if(snapshot.connectionState==ConnectionState.waiting){
        return const Scaffold(
          body: Center(child: CircularProgressIndicator()),
        );
      }
      if(snapshot.hasData){
        return DashboardScreen();
      } 
      return HomeScreen();
    }
    );
  }
}
