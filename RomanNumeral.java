/*Program: RomanNumeral
 *Description: This program asks the user for either an integer or a Roman numeral and then converts it into the other. 
 *By: Gabe Skorski
 *Date: 10.20.2017
 */

package com.gabeskorski.apps;

import java.util.Scanner;

public class RomanNumeral
{
	public static Boolean programRunning = true;
	public static final String ERROR_MESSAGE = " is invalid input";

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		while (programRunning)
		{

			System.out.println("Please enter either an integer or a Roman numeral between 1 and 3999. (-1 to QUIT)");

			String number = input.nextLine();

			try
			{
				int integer = Integer.parseInt(number);
				if (integer == -1)
					System.exit(0);
				if (integer > 0 && integer < 4000)
				{
					System.out.println(integer + " is " + itorn(integer));
				} else
				{
					System.out.println(integer + ERROR_MESSAGE);
				}
			} catch (NumberFormatException e)
			{
				rntoi(number);
			}
		}
		input.close();
	}

	// roman numeral to integer
	public static void rntoi(String Rn)
	{
		Boolean isValid = false;
		for (int i = 0; i < 4000; i++)
		{
			if (itorn(i).equals(Rn))
			{
				System.out.println(Rn + " is " + i);
				isValid = true;
				break;
			}
		}
		if (isValid == false)
			System.out.println(Rn + ERROR_MESSAGE);
	}

	// integer to roman numeral
	public static String itorn(int n)
	{
		StringBuffer romanNumeral = new StringBuffer("");

		while (n / 1000 > 0)
		{
			romanNumeral.append('M');
			n -= 1000;
		}
		while (n / 900 > 0)
		{
			romanNumeral.append("CM");
			n -= 900;
		}
		while (n / 500 > 0)
		{
			romanNumeral.append('D');
			n -= 500;
		}
		while (n / 400 > 0)
		{
			romanNumeral.append("CD");
			n -= 400;
		}
		while (n / 100 > 0)
		{
			romanNumeral.append('C');
			n -= 100;
		}
		while (n / 90 > 0)
		{
			romanNumeral.append("XC");
			n -= 90;
		}
		while (n / 50 > 0)
		{
			romanNumeral.append('L');
			n -= 50;
		}
		while (n / 40 > 0)
		{
			romanNumeral.append("XL");
			n -= 40;
		}
		while (n / 10 > 0)
		{
			romanNumeral.append('X');
			n -= 10;
		}
		while (n / 9 > 0)
		{
			romanNumeral.append("IX");
			n -= 9;
		}
		while (n / 5 > 0)
		{
			romanNumeral.append('V');
			n -= 5;
		}
		while (n / 4 > 0)
		{
			romanNumeral.append("IV");
			n -= 4;
		}
		while (n / 1 > 0)
		{
			romanNumeral.append('I');
			n -= 1;
		}
		return romanNumeral.toString();
	}
}