/*Program: Pi
 *Description: This program implements the "TBI" methods of class PI 
 *			   and defines Abstract methods from extended class Number.
 *By: Gabe Skorski
 *Date: 11.11.2017
 */

package com.gabeskorski.apps;

/*
 * Pi is a number.
 *
 * @creator gdt
 * @created 02017.02.26
 *
 * Answer the following questions here in this file comment block
 * prior to submitting this file to the instructor.
 *
 * (0) No or Yes: PI objects are immutable.  
 *     No, because the class "Pi" and the variable "PI" are public and not private and/or final.
 *
 * (1) The expression (new Pi().getPi() == Pi.PI) evaluates to what? 
 *     True.
 *
 * (2) Briefly explain why the byteValue() and shortValue()
 *     methods did not need to be implemented in class Pi.
 *     They are not Abstract methods and are already implemented in their class, 
 *     they utilize intValue which is Abstract and must be defined first however.
 */

public class Pi extends Number {

   public static double PI = 3.14159265358979323846264338327950;

   private int intValue;        // PI rounded down to nearest int
   private long longValue;      // PI rounded up to nearest int
   private float floatValue;    // PI as type float
   private double doubleValue;  // PI as defined

   /*
    * TBI (To Be Implemented)
    * The constructor assigns values to all of the instance 
    * variables defined above. The values that are assigned
    * to the instance variables are documented using comments
    * when the instance variables are defined.
    */
   public Pi() {
      // the expressions on the right side of each of the following 
      // assignment statements must use PI in them...
      intValue = (int) Math.floor(PI);
      longValue = (long) Math.ceil(PI);
      floatValue = (float)PI;
      doubleValue = PI;
   }

   /*
    * TBI (To Be Implemented)
    * Returns a String representation of this Pi object
    * that is used as the output of this progam.
    */
   public String toString() {
	   String outPut = "byteValue(): " + byteValue()
	   		+ "\nshortValue(): " + shortValue()
	   		+ "\nintValue(): " + intValue()
	   		+ "\nlongValue(): " + longValue()
	   		+ "\nfloatValue(): " + floatValue()
	   		+ "\ndoubleValue(): " + doubleValue();
	   
	   return outPut;
   }
   
   @Override
   public int intValue()
   {
	   return intValue;
   }

   @Override
   public long longValue()
   {
   	return longValue;
   }

   @Override
   public float floatValue()
   {
   	return floatValue;
   }

   @Override
   public double doubleValue()
   {
   	return doubleValue;
   }
   
   
   
   

   /*
    * The following methods cannot be modified/deleted.
    */
   public static void main(String[] argv) {
      System.out.println(new Pi());
   }

   public double getPi() { return doubleValue; }



}

/*
 * the output of your program must match the following
 *

byteValue(): 3
shortValue(): 3
intValue(): 3
longValue(): 4
floatValue(): 3.1415927
doubleValue(): 3.141592653589793

 *
 */