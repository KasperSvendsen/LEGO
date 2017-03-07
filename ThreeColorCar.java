import lejos.nxt.*;
import lejos.util.*;

public class ThreeColorCar
{
  private static Stopwatch timer = new Stopwatch();
  private static int elapsed;


  public static void main (String[] aArg)
  throws Exception
  {
   final int power = 80;

   ThreeColorSensor sensor = new ThreeColorSensor(SensorPort.S3);

   sensor.calibrate();
   timer.reset();

   LCD.clear();
   LCD.drawString("Light: ", 0, 2); 

   while (! Button.ESCAPE.isDown())
   {

    LCD.drawInt(sensor.light(),4,10,2);
    LCD.refresh();

    if (timer.elapsed() > 250)
      Car.stop();

    else if ( sensor.black() ){
      Car.forward(power, 0);
      timer.reset();
    }
    else if ( sensor.white() ){
      Car.forward(0, power);
      timer.reset();
    }
    
    else if ( sensor.green() ) 
     Car.forward(power, power);




   Thread.sleep(10);
 }

 Car.stop();
 LCD.clear();
 LCD.drawString("Program stopped", 0, 0);
 LCD.refresh();
}
}