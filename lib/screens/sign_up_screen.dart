import 'package:flutter/material.dart';
import 'package:maxpense/controllers/auth_controller.dart';
import 'package:maxpense/screens/sign_in_screen.dart';
import 'package:maxpense/screens/dashboard_screen.dart';
import 'package:firebase_auth/firebase_auth.dart';

class SignUpScreen extends StatefulWidget {
  const SignUpScreen({super.key});

  @override
  State<SignUpScreen> createState() => _SignUpScreenState();
}

class _SignUpScreenState extends State<SignUpScreen> {
  final emailText = TextEditingController();
  final nameText = TextEditingController();
  final passText = TextEditingController();

  bool _isLoading = false;
  bool _obscurePassword = true;

  Future<void> _registerUser() async {
    final email = emailText.text.trim();
    final password = passText.text.trim();
    final name = nameText.text.trim();

    if (email.isEmpty || password.isEmpty || name.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Please fill all fields')),
      );
      return;
    }

    setState(() => _isLoading = true);

    try {
      final user = await AuthController.register(
        name: name,
        email: email,
        password: password,
      );

      if (user != null) {
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text("Account created successfully!")),
        );
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (context) => const DashboardScreen()),
        );
      } else {
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text("Registration failed — unknown reason")),
        );
      }
    } on FirebaseAuthException catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Auth Error: ${e.code} - ${e.message}")),
      );
    } on FirebaseException catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Firebase Error: ${e.code} - ${e.message}")),
      );
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text("Unexpected Error: $e")),
      );
    } finally {
      setState(() => _isLoading = false);
    }
  }

  Widget _Label(String text) {
    return Text(
      text,
      style: const TextStyle(fontWeight: FontWeight.w500, fontSize: 13),
    );
  }

  Widget _TextField({
    required TextEditingController controller,
    required IconData icon,
    required String hint,
    TextInputType? keyboardType,
    bool obscureText = false,
    Widget? suffix,
  }) {
    return TextField(
      controller: controller,
      keyboardType: keyboardType,
      obscureText: obscureText,
      style: const TextStyle(fontSize: 14),
      decoration: InputDecoration(
        prefixIcon: Icon(icon, size: 18),
        suffixIcon: suffix,
        hintText: hint,
        hintStyle: const TextStyle(fontSize: 13),
        contentPadding: const EdgeInsets.symmetric(horizontal: 12, vertical: 12),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(8),
          borderSide: const BorderSide(color: Color(0xFF575DFB), width: 1),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(8),
          borderSide: const BorderSide(color: Color(0xFF575DFB), width: 1.2),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 18, vertical: 12),
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                IconButton(
                  icon: const Icon(Icons.arrow_back, size: 22),
                  onPressed: () => Navigator.pop(context),
                ),
                const SizedBox(height: 6),

                const Text(
                  "Register",
                  style: TextStyle(
                    fontSize: 24,
                    fontWeight: FontWeight.bold,
                    color: Color(0xFF575DFB),
                  ),
                ),
                const SizedBox(height: 3),
                const Text(
                  "Create an account to access all the features of MaxPense!",
                  style: TextStyle(fontSize: 13, color: Colors.black54),
                ),
                const SizedBox(height: 22),

                _Label("Your Name"),
                _TextField(
                  controller: nameText,
                  icon: Icons.person_outline,
                  hint: 'Ex: John Doe',
                ),
                const SizedBox(height: 10),

                _Label("Email"),
                _TextField(
                  controller: emailText,
                  icon: Icons.alternate_email,
                  hint: 'Ex: abc@example.com',
                  keyboardType: TextInputType.emailAddress,
                ),
                const SizedBox(height: 10),

                _Label("Password"),
                _TextField(
                  controller: passText,
                  icon: Icons.lock_outline,
                  hint: '***********',
                  obscureText: _obscurePassword,
                  suffix: IconButton(
                    icon: Icon(
                      _obscurePassword ? Icons.visibility_off : Icons.visibility,
                      size: 18,
                    ),
                    onPressed: () {
                      setState(() {
                        _obscurePassword = !_obscurePassword;
                      });
                    },
                  ),
                ),
                const SizedBox(height: 22),

                SizedBox(
                  width: double.infinity,
                  height: 48,
                  child: ElevatedButton(
                    onPressed: _isLoading ? null : _registerUser,
                    style: ElevatedButton.styleFrom(
                      backgroundColor: const Color(0xFF575DFB),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                    ),
                    child: _isLoading
                        ? const CircularProgressIndicator(color: Colors.white, strokeWidth: 2)
                        : const Text("Register",
                            style: TextStyle(fontSize: 16, color: Colors.white)),
                  ),
                ),
                const SizedBox(height: 16),

                Row(
                  children: [
                    Expanded(child: Divider(color: Colors.grey[400], thickness: 0.8)),
                    const Padding(
                      padding: EdgeInsets.symmetric(horizontal: 6.0),
                      child: Text("or", style: TextStyle(fontSize: 13)),
                    ),
                    Expanded(child: Divider(color: Colors.grey[400], thickness: 0.8)),
                  ],
                ),
                const SizedBox(height: 16),

                SizedBox(
                  width: double.infinity,
                  height: 48,
                  child: OutlinedButton.icon(
                    onPressed: () async {
                      try {
                        await AuthController.signInWithGoogle();
                        Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => const DashboardScreen()),
                        );
                      } catch (e) {
                        print("Google Sign-In Error: $e");
                      }
                    },
                    icon: Image.asset("assets/google-logo.jpg", height: 20),
                    label: const Text(
                      "Continue with Google",
                      style: TextStyle(color: Colors.black87, fontSize: 14),
                    ),
                    style: OutlinedButton.styleFrom(
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                      side: const BorderSide(color: Colors.black87, width: 1),
                    ),
                  ),
                ),
                const SizedBox(height: 16),

                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text("Already have an account?", style: TextStyle(fontSize: 13)),
                    TextButton(
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => SigninScreen()),
                        );
                      },
                      child: const Text(
                        "Login",
                        style: TextStyle(color: Color(0xFF575DFB), fontSize: 13),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
