#include <Arduino.h>
#include <TM1637Display.h>

// Define the connections pins
#define CLK 2
#define DIO 3
TM1637Display display(CLK, DIO); // Create an instance of the TM1637Display


void setup() {
  display.setBrightness(5);  // Set the display brightness (0-7)
};


void loop() {
  display.clear();  // Clear the display
  uint8_t data[] = {
      SEG_A | SEG_B | SEG_C,                  // T
      SEG_A | SEG_F | SEG_G | SEG_E | SEG_D,  // E
      SEG_A | SEG_F | SEG_G | SEG_C | SEG_D,  // S
      SEG_A | SEG_F | SEG_E                   // T
    };
  display.setSegments(data); // Display the data
  delay(1500);

  ////// Display Number
  display.clear();  // Clear the display
  long randNumber = random(0, 9999);        // Generate a random number between 0 and 9999
  display.showNumberDec(randNumber, false); // Display the number 
  delay(1500);
};
