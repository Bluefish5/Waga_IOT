#include "TM1637Display.h"
#include "HX711.h"

// Buttons connections
#define SWITCH_CHCIKEN_PIN 4
#define SWITCH_NORMAL_PIN 5
#define BUTTON_TARA_PIN 6

// Display connections
#define DISPLAY_CLK 2
#define DISPLAY_DIO 3

// HX711 connections
#define LOADCELL_DOUT_PIN 2;
#define LOADCELL_SCK_PIN 3;


TM1637Display display(CLK, DIO);
HX711 scale;
int buttonState = 0;


void setup() {
  Serial.begin(115200);  

  pinMode(SWITCH_CHCIKEN_PIN, INPUT);
  pinMode(SWITCH_NORMAL_PIN, INPUT);
  pinMode(BUTTON_TARA_PIN, INPUT);

  scale.begin(LOADCELL_DOUT_PIN, LOADCELL_SCK_PIN);

  display.setBrightness(5);
  display.clear();

  //attachInterrupt(digitalPinToInterrupt(motionSensor), detectsMovement, RISING);
}

void loop() {
    // loading scale => animation on display
    if (scale.is_ready()) {
        scale.set_scale();  

        // Display animation, wainting sequence
        Serial.println("Tare... remove any weights from the scale.");
        delay(5000);

        scale.tare();
        delay(5000);

        // scale loaded => take meseurments, display it, wait for interupt, BT
        while (true)
        {
            long reading = scale.get_units(10);
            display.showNumberDec(reading, false);
        }
        
        
  } 

  delay(1);
}

//   buttonState = digitalRead(buttonPin);
//   if (buttonState == HIGH);