import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: LedPage(),
    );
  }
}

class LedPage extends StatelessWidget {
  const LedPage({super.key});

  final String baseUrl = "http://10.0.2.2:8080/api/led";

  Future<void> ledOn() async {
    await http.post(Uri.parse("$baseUrl/on"));
  }

  Future<void> ledOff() async {
    await http.post(Uri.parse("$baseUrl/off"));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("IoT LED Control")),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: ledOn,
              child: const Text("LED ON"),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: ledOff,
              child: const Text("LED OFF"),
            ),
          ],
        ),
      ),
    );
  }
}
