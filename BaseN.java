package com.gabeskorski.apps;

/*Program: BaseN
 *Description: This program gets a number and a base from the user and prints 
 *			   the base-10 value of the number in the entered base.
 *By: Gabe Skorski
 *Date: 10.06.2017
 */

import java.util.Scanner;

public class BaseN
{

	public static final int BASEN_ERRNO = -1;

	public static void main(String[] argv)
	{

		Scanner input = new Scanner(System.in);
		System.out.print("enter number followed by base (e.g. 237 8): ");
		int number = input.nextInt();
		int base = input.nextInt();
		input.close();
		int answer = basen(number, base, 1, 0);

		if (BASEN_ERRNO == answer)
			System.out.println("***error: " + number + " is not a valid base-" + base + "number");
		else
			System.out.println(number + " base-" + base + " = " + answer);
	}

	public static int basen(int number, int base, int placeValue, int answer)
	{
		if (number == 0)
			return answer;

		int digit = number % 10;
		if (digit >= base)
			return BASEN_ERRNO;
		answer += digit * placeValue;
		placeValue *= base;
		number /= 10;
		return basen(number, base, placeValue, answer);
	}

}