package com.gabeskorski.apps;

/*
Program: ValleyPeak
Description: This program identifies the valleys and peaks that are found in an array of ints.
By Gabe Skorski
09.20.2017
*/


public class ValleyPeak {
   public static void main(String[] argv) {

      int[][] arrays = { 
         { 2, 5, 3, 2, 1, 9, 7, 8, },
         { 4, 4, 7, 4, 2, },
         { 2, 5, 3, 3, 7, 2, },
         { 1, 9, 3, 5, 2, 7, 3, 5, 1 },
         { 2, 8, 8, 7, 9, 9, 9, 3 },
         { 4, 3, 2, 1, 0, },
         { 1, 2, 5, 9, 10, },
      };

      for (int i = 0; i < arrays.length; i++) 
         valley_peak(arrays[i]);
   }

   static void valley_peak(int[] a) {

	   int[] arrays = a;
	   System.out.print("\narray: ");
	   for(int i = 0; i < arrays.length; i++)
	   System.out.print(arrays[i] + " ");
	   	   
	   System.out.println("");
	   for(int i = 0; i < arrays.length; i++){

		   if (i == 0){
		   if (arrays[i] < arrays[i+1]){
			   System.out.println("[" + i + "]=" + arrays[i] + " is a valley");
		   } else if (arrays[i] > arrays[i+1]){
			   System.out.println("[" + i + "]=" + arrays[i] + " is a peak");
		   }
	   } else if (i == (arrays.length - 1)){
		   if (arrays[i] < arrays[i-1]){
			   System.out.println("[" + i + "]=" + arrays[i] + " is a valley");
		   } else if (arrays[i] > arrays[i-1]){
			   System.out.println("[" + i + "]=" + arrays[i] + " is a peak");
		   }
	   } else  {
		   if (arrays[i] < arrays[i-1] && arrays[i] < arrays[i+1]){
			   System.out.println("[" + i + "]=" + arrays[i] + " is a valley");
		   } else if (arrays[i] > arrays[i-1] && arrays[i] > arrays[i+1]){
			   System.out.println("[" + i + "]=" + arrays[i] + " is a peak");
		   }
	   } 
	   
	   }
   }
   
   

}

/*
 * your program's output must match the following...
 *

array: 2 5 3 2 1 9 7 8 
[0]=2 is a valley
[1]=5 is a peak
[4]=1 is a valley
[5]=9 is a peak
[6]=7 is a valley
[7]=8 is a peak

array: 4 4 7 4 2 
[2]=7 is a peak
[4]=2 is a valley

array: 2 5 3 3 7 2 
[0]=2 is a valley
[1]=5 is a peak
[4]=7 is a peak
[5]=2 is a valley

array: 1 9 3 5 2 7 3 5 1 
[0]=1 is a valley
[1]=9 is a peak
[2]=3 is a valley
[3]=5 is a peak
[4]=2 is a valley
[5]=7 is a peak
[6]=3 is a valley
[7]=5 is a peak
[8]=1 is a valley

array: 2 8 8 7 9 9 9 3 
[0]=2 is a valley
[3]=7 is a valley
[7]=3 is a valley

array: 4 3 2 1 0 
[0]=4 is a peak
[4]=0 is a valley

array: 1 2 5 9 10 
[0]=1 is a valley
[4]=10 is a peak

 *
 */