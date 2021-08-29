import 'package:flutter/material.dart';

void main() {
  runApp(DiaryApp());
}

@pragma('vm:entry-point')
void lockMain() => runApp(LockScreenApp());

class DiaryApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(),
    );
  }
}

class LockScreenApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(),
    );
  }
}
