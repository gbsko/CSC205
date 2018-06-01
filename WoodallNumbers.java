package com.gabeskorski.apps;

/*
Program: Woodall Numbers
Description: This program prints the first HOW_MANY Woodall Numbers. 
			 Woodall Numbers are of the form:  f(n) = n * 2^n - 1.
By: Gabe Skorski
10.04.2017
 */

public class WoodallNumbers
{

	// class variables...
	private static final int HOW_MANY = 32;
	private static final int NUMBERS_PER_LINE = 5;
	private static final String SEPARATOR = " ";

	// instance variable...
	private long[] numbers;

	public static void main(String[] argv)
	{
		WoodallNumbers w = new WoodallNumbers();
		w.print();
	}

	public WoodallNumbers()
	{
		numbers = new long[HOW_MANY + 1];
		for (int i = 1; i < HOW_MANY + 1; i++)
		{
			numbers[i] = i * exponent(2, i) - 1;
		}
	}

	public long exponent(long b, long e)
	{
		if (e == 0)
		{
			return 1;
		} else if (e == 1)
		{
			return b;
		} else
		{
			long answer = b;
			while (e > 1)
			{
				answer = answer * b;
				e--;
			}
			return answer;
		}
	}

	public void print()
	{
		System.out.println("the first " + HOW_MANY + " Woodall Numbers " + "\n============================");
		for (int i = 1; i < HOW_MANY + 1; i++)
		{
			System.out.print(numbers[i] + SEPARATOR);
			if (i % NUMBERS_PER_LINE == 0)
			{
				System.out.println();
			}
		}
	}
}