import lejos.nxt.*;
import lejos.util.*;
import java.lang.Math;
public class PIDLineFollower
{

  public static void main (String[] aArg)
  throws Exception
  {
     

     BlackWhiteSensor sensor = new BlackWhiteSensor(SensorPort.S3);
	 

     sensor.calibrate();
	 
     LCD.clear();
     LCD.drawString("Light: ", 0, 2); 

     float kp = 0.9f;
     float ki = 0.033f;
     float kd = 6.19f;
     float offset = sensor.threshold();
     float tp = 75f;
     float integral = 0f;
     float lastError = 0f;
     float derivative = 0f;
     int i = 0;
    
	 
     while (! Button.ESCAPE.isDown() && i < 1000)
     {

	     LCD.drawInt(sensor.light(),4,10,2);
	     LCD.refresh();

	     float lightValue = sensor.light();
	     float error = lightValue - offset;
	     integral = integral + error;
	     derivative = error - lastError;
	     float turn = kp * error + ki * integral + kd * derivative;
	     turn = turn ;
	     int powerA = Math.round(tp + turn);
	     int powerC = Math.round(tp - turn);

	     
	     Car.forward(powerA, powerC);

	     lastError = error;
	   	
	    	i++;

	     Thread.sleep(10);

     }
     

     Car.stop();
     LCD.clear();
     LCD.drawString("Program stopped", 0, 0);
     LCD.refresh();
   }
}