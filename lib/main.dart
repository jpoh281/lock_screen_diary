import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

var platform = MethodChannel("com.jpoh281.lock_screen_diary");

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(ProviderScope(child: DiaryApp()));
}

@pragma('vm:entry-point')
void lockMain() => runApp(ProviderScope(child: LockScreenApp()));

class DiaryApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    platform.invokeMethod('checkPermission');
    platform.invokeMethod('getPermission');
    platform.invokeMethod('startService');

    return MaterialApp(
      home: Scaffold(
        body: Column(),
      ),
    );
  }
}

class LockScreenApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.blue,
        body: SafeArea(
          child: Column(
            children: [
              Center(
                child: Text("잠금화면"),
              ),
              TextButton(
                child: Text("잠금해제", style: TextStyle(color: Colors.pink, fontSize: 20),),
                onPressed: (){
                  SystemNavigator.pop();
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
