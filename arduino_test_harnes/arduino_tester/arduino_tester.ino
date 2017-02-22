#include <SoftwareSerial.h>
#include "bluetooth.h"

Bluetooth *blue = new Bluetooth("ExampleRobot");

int inPin = 7;   // pushbutton connected to digital pin 7
void setup(){
	Serial.begin(9600);
	blue->setupBluetooth();	
  pinMode(inPin, INPUT);
}


void loop(){
	String msg = blue->Read();
	if(msg.length() > 1){
    	Serial.print("len: ");
    	Serial.println(msg.length());
		Serial.print("Received: ");
			Serial.println(msg);
		if(msg.equals("gls#")){
			// while(!Serial.available()){;}
   		    boolean val = digitalRead(inPin);
			Serial.println(val);
    	   	if(val){
    	   	   	blue->Send("on#");
    	   	}else{
         		blue->Send("off#");
    	    }
		}
	}
	// if(Serial.available()){

	// 	blue->Send("Example message#");
	// }
}
