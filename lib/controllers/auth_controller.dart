import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';

class AuthController {
  static final FirebaseAuth _auth = FirebaseAuth.instance;
  static final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  static final GoogleSignIn _googleSignIn = GoogleSignIn();

  static User? getCurrentUser() {
    return _auth.currentUser;
  }

  // Register
  static Future<User?> register({
    required String name,
    required String email,
    required String password,
  }) async {
    try {
      UserCredential userCred = await _auth.createUserWithEmailAndPassword(
        email: email,
        password: password,
      );

      await userCred.user?.updateDisplayName(name);
      await userCred.user?.reload();

      await _firestore.collection("users").doc(userCred.user!.uid).set({
        "name": name,
        "email": email,
        "createdAt": FieldValue.serverTimestamp(),
      });

      return userCred.user;
    } catch (e) {
      print("Register Error: $e");
      rethrow;
    }
  }

  //Login
  static Future<User?> login({
    required String email,
    required String password,
  }) async {
    try {
      UserCredential userCred = await _auth.signInWithEmailAndPassword(
        email: email,
        password: password,
      );
      return userCred.user;
    } catch (e) {
      print("Login Error: $e");
      rethrow;
    }
  }

  // Google Sign-In
  static Future<User?> signInWithGoogle() async {
    try {
      final GoogleSignInAccount? googleUser = await _googleSignIn.signIn();
      if (googleUser == null) return null;

      final GoogleSignInAuthentication googleAuth = await googleUser.authentication;

      final OAuthCredential credential = GoogleAuthProvider.credential(
        accessToken: googleAuth.accessToken,
        idToken: googleAuth.idToken,
      );

      await _auth.signInWithCredential(credential);
      return _auth.currentUser;
    } catch (e) {
      print("Google Auth Error: $e");
      return null;
    }
  }

  // Logout
  static Future<void> logout() async {
    try {
      final user = _auth.currentUser;

      if (user != null) {
        for (var provider in user.providerData) {
          if (provider.providerId == 'google.com') {
            await _googleSignIn.signOut();
            break;
          }
        }
      }

      await _auth.signOut();

      print("✅ User signed out successfully.");
    } catch (e) {
      print("❌ Logout error: $e");
    }
  }
}
