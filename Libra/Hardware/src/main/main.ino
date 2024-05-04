#include "BluetoothSerial.h"
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

// Check if Bluetooth configs are enabled
#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif


TM1637Display display(CLK, DIO);
HX711 scale;
BluetoothSerial SerialBT;

String message = "";
char incomingChar;

uint8_t frameCursor = 0;
uint8_t frameSets[][]=
    {SEG_A,SEG_A,SEG_A,SEG_A},
    {SEG_B,SEG_B,SEG_B,SEG_B},
    {SEG_C,SEG_C,SEG_C,SEG_C},
    {SEG_D,SEG_D,SEG_D,SEG_D},
    {SEG_E,SEG_E,SEG_E,SEG_E},
    {SEG_E,SEG_E,SEG_E,SEG_E};

uint8_t* getFrame() return frameSets[frameCursor++];

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


  Serial.begin(115200); 
  SerialBT.begin("WAGA_BT"); 

  pinMode(SWITCH_CHCIKEN_PIN, INPUT);
  pinMode(BUTTON_TARA_PIN, INPUT);

  scale.begin(LOADCELL_DOUT_PIN, LOADCELL_SCK_PIN);

  display.setBrightness(5);
  display.clear();

  
  while(!scale.is_ready())delay(1);

  scale.set_scale();

  savedTime = millis();
  while(!(timeDiffrence >= 10000UL) )
  {
    actualTime = millis();
    timeDiffrence = actualTime - savedTime;
    timeDiffrenceAnimation = actualTime - savedTimeAnimation;
    if(timeDiffrenceAnimation >= 500UL)
    {
      savedTimeAnimation = actualTime;
      display.setSegments(getFrame());
    }
  }
  scale.tare();
  }


void loop() {
  reading = scale.get_units(10);
  display.showNumberDec(reading, false);
  if (SerialBT.available()){
    char incomingChar = SerialBT.read();
    if (incomingChar != '\n'){
      message += String(incomingChar);
    }
    else{
      message = "";
    }
  }
  if (message =="1"){
    SerialBT.println(reading);
  }
        
}

