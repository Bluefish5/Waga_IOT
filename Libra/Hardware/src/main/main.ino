#include "BluetoothSerial.h"
#include "TM1637Display.h"
#include "HX711.h"

// Buttons connections
#define SWITCH_CHCIKEN_PIN 8
#define SWITCH_NORMAL_PIN 8
#define BUTTON_TARA_PIN 8

// Display connections
#define DISPLAY_CLK 13
#define DISPLAY_DIO 27

// HX711 connections
#define LOADCELL_DOUT_PIN 2
#define LOADCELL_SCK_PIN 4

// Check if Bluetooth configs are enabled
#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif


TM1637Display display(DISPLAY_CLK, DISPLAY_DIO);
HX711 scale;
BluetoothSerial SerialBT;

String message = "";
char incomingChar;

uint8_t frameCursor = 0;
uint8_t frameSets[][4] = {
    {SEG_A, SEG_A, SEG_A, SEG_A},
    {SEG_B, SEG_B, SEG_B, SEG_B},
    {SEG_C, SEG_C, SEG_C, SEG_C},
    {SEG_D, SEG_D, SEG_D, SEG_D},
    {SEG_E, SEG_E, SEG_E, SEG_E},
    {SEG_F, SEG_F, SEG_F, SEG_F}};

uint8_t* getFrame(){
  if(frameCursor>5) frameCursor = 0;
  return frameSets[frameCursor++];

}


unsigned long actualTime = 0;
unsigned long savedTime = 0;
unsigned long savedTimeAnimation = 0;
unsigned long timeDiffrence = 0;
unsigned long timeDiffrenceAnimation = 0;

int buttonState = 0;

long reading = 0;

void setup() {

  // attachInterrupt(SWITCH_CHCIKEN_PIN, detectsMovement, RISING);
  // attachInterrupt(SWITCH_CHCIKEN_PIN, detectsMovement, RISING);


  //Serial.begin(115200); 

  pinMode(16, OUTPUT);
  pinMode(18, OUTPUT);
  pinMode(21, OUTPUT);

  //SerialBT.begin("WAGA_BT"); 

  // pinMode(SWITCH_CHCIKEN_PIN, INPUT);
  // pinMode(BUTTON_TARA_PIN, INPUT);

  scale.begin(LOADCELL_DOUT_PIN, LOADCELL_SCK_PIN);

  display.setBrightness(3);
  display.clear();

  
  while(!scale.is_ready())delay(1);

  scale.tare(20);

  scale.set_offset(4294844575);
  scale.set_scale(-428.429047);
  
  savedTime = millis();
  while(!(timeDiffrence >= 5000UL) )
  {
    actualTime = millis();
    timeDiffrence = actualTime - savedTime;
    timeDiffrenceAnimation = actualTime - savedTimeAnimation;
    if(timeDiffrenceAnimation >= 500UL)
    {
      savedTimeAnimation = actualTime;
      display.clear();
      display.setSegments(getFrame());
    }
  }
  scale.tare();
  }


void loop() {
  reading = scale.get_units(5);
  if(reading > 90 && reading < 200)
  {
    digitalWrite(18, HIGH);
    digitalWrite(16, LOW);

    digitalWrite(21, HIGH);
    delay(200);

    digitalWrite(21, LOW);
    delay(100);

    digitalWrite(21, HIGH);
    delay(200);

    digitalWrite(21, LOW);
    delay(100);

    digitalWrite(21, HIGH);
    delay(200);

    digitalWrite(21, LOW);
    delay(100);
  }
  else
  {
    digitalWrite(16, HIGH);
    digitalWrite(18, LOW);
  }
  display.showNumberDec(reading, false);
  //potem do BT
  // if (SerialBT.available()){
  //   char incomingChar = SerialBT.read();
  //   if (incomingChar != '\n'){
  //     message += String(incomingChar);
  //   }
  //   else{
  //     message = "";
  //   }
  // }
  // if (message =="1"){
  //   SerialBT.println(reading);
  // }
        
}

