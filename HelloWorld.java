package com.gabeskorski.apps;

/*
 * This Java application prints the phrase hello, world followed by
 * a newline to the standard output stream. This program was program
 * generated.
 *
 * @creator Gabe Skorski
 * @generated 02017/08/30
 */

public class HelloWorld
{
	public static void main(String[] argv)
	{
		int n = 2345;
		String nString = Integer.toString(n);
		
		
		int[] nArray = new int[nString.length()];
		
		for (int i = 0; i < nString.length(); i++)
		{
			nArray[i] = Character.getNumericValue(nString.charAt(i));
		}
	
	

		

		for (int i = 0; i < nString.length(); i++)
		{
	        System.out.println(nArray[i]);
		}
		
		
	}
}