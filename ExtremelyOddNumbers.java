package com.gabeskorski.apps;

/*
Program: ExtremelyOddNumbers
Description: This Java application checks whole numbers in the interval [101, 100001] to see if they are "extremely odd" and "super extremely odd" numbers.
By Gabe Skorski
09.07.2017
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExtremelyOddNumbers
{

	static final int MAX = 100001;
	static final int MIN = 101;
	static final int EXIT = -1; // sentinel value

	static final String PROMPT = "Enter whole number in the interval [" + MIN + ", " + MAX + "] (" + EXIT
			+ " to exit): ";

	public static void main(String[] argv)
	{

		// initialize keyboard and get user input
		Scanner in = new Scanner(System.in);
		do
		{
			int n = getInput(in);
			if (n == EXIT)
				break;
			eon(n);
		} while (true);
		in.close();

	}

	/**
	 * Return an int that is read using a Scanner parameter.
	 *
	 * @param scanner
	 *            object of type Scanner used to read input
	 * @return user entered int
	 */
	static int getInput(Scanner scanner)
	{

		final String ERR_MSG_PREFIX = "*** error: ";
		int n = 0;

		do
		{
			System.out.print(PROMPT);
			try
			{
				n = scanner.nextInt();
			} catch (InputMismatchException e)
			{
				System.out.println(ERR_MSG_PREFIX + "whole number input only");
				scanner.nextLine();
				continue;
			}
			if (n == EXIT)
				return n;
			if (n < MIN)
				System.out.println(ERR_MSG_PREFIX + "input must be >= " + MIN);
			else if (n > MAX)
				System.out.println(ERR_MSG_PREFIX + "input must be <= " + MAX);
			else
				return n;
		} while (true);

	}

	// check user input against checks
	public static void eon(int n)
	{

		String statement;

		if (check1(n) == true && check2(n) == true && check3(n) == true && check4(n) == true && check5(n) == true
				&& check6(n) == true && check7(n) == true)
		{
			statement = " is super extremely odd";
		} else if (check1(n) == true && check2(n) == true && check3(n) == true && check4(n) == true
				&& check5(n) == true)
		{
			statement = " is extremely odd";
		} else
		{
			statement = " is *not* extremely odd";
		}
		System.out.println(n + statement);
	}

	// it's an odd number
	public static boolean check1(int n)
	{
		if (n % 2 != 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	// it has an odd number of digits
	public static boolean check2(int n)
	{
		int length = String.valueOf(n).length();
		if (length % 2 != 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	// all of its digits are odd numbers
	public static boolean check3(int n)
	{
		String nString = Integer.toString(n);
		int[] nArray = new int[nString.length()];

		for (int i = 0; i < nString.length(); i++)
		{
			nArray[i] = Character.getNumericValue(nString.charAt(i));
		}

		boolean odd = true;
		for (int i = 0; i < nString.length(); i++)
		{
			if (nArray[i] % 2 == 0)
				odd = false;
		}
		return odd;
	}

	// the sum of its digits is an odd number
	public static boolean check4(int n)
	{
		int num = n;
		int sum = 0;
		while (num > 0)
		{
			sum = sum + num % 10;
			num = num / 10;
		}
		if (sum % 2 != 0)
		{
			return true;
		} else
		{
			return false;
		}

	}

	// the product of its digits is an odd number
	public static boolean check5(int n)
	{
		int num = n;
		int prod = 1;
		while (num > 0)
		{
			prod = prod * (num % 10);
			num = num / 10;
		}
		if (prod % 2 != 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	// all digits comprising the sum of its digits are odd numbers
	public static boolean check6(int n)
	{
		int num = n;
		int sum = 0;
		while (num > 0)
		{
			sum = sum + num % 10;
			num = num / 10;
		}

		String nString = Integer.toString(sum);
		int[] nArray = new int[nString.length()];

		for (int i = 0; i < nString.length(); i++)
		{
			nArray[i] = Character.getNumericValue(nString.charAt(i));
		}

		boolean odd = true;
		for (int i = 0; i < nString.length(); i++)
		{
			if (nArray[i] % 2 == 0)
				odd = false;
		}
		return odd;

	}

	// all digits comprising the product of its digits are odd numbers
	public static boolean check7(int n)
	{
		int num = n;
		int prod = 1;
		while (num > 0)
		{
			prod = prod * (num % 10);
			num = num / 10;
		}

		String nString = Integer.toString(prod);
		int[] nArray = new int[nString.length()];

		for (int i = 0; i < nString.length(); i++)
		{
			nArray[i] = Character.getNumericValue(nString.charAt(i));
		}

		boolean odd = true;
		for (int i = 0; i < nString.length(); i++)
		{
			if (nArray[i] % 2 == 0)
				odd = false;
		}
		return odd;
	}

}