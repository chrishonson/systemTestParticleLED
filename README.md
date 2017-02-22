# systemTestParticleLED
HIL system testing an Android app, and Particle cloud using an Arduino with Bluetooth
=======================

Example project to illustrate the idea of testing not just your Android app, but also the connection to the cloud, the cloud reaction, and the IoT device reaction to the cloud. This is facilitate by a bluetooth connection from the Android instrumentation app to an Arduino that is physically connected to the Particle Photon. 
Simply put: this is a simple web connected led example. When you turn the switch on on the app it turns the led on on the Particle Photon. What's cool about this project is there's an instrumentation test that runs on the Android device and it verifies that the led actually turns on after it presses the switch in the Android app. 

I pieced together a couple example apps here including:
Android with Arduino - Bluetooth
https://github.com/aron-bordin/Android-with-Arduino-Bluetooth#android-with-arduino---bluetooth

LED example app here
https://docs.particle.io/guide/getting-started/examples/photon/#control-leds-over-the-39-net