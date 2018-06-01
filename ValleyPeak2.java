package com.gabeskorski.apps;

/*
Program: ValleyPeak
Description: This program identifies the valleys and peaks that are found in an array of ints.
By Gabe Skorski
09.20.2017
*/

public class ValleyPeak2
{
	public static void main(String[] argv)
	{

		int[][] arrays =
		{
				{ 2, 5, 3, 2, 1, 9, 7, 8, },
				{ 4, 4, 7, 4, 2, },
				{ 2, 5, 3, 3, 7, 2, },
				{ 1, 9, 3, 5, 2, 7, 3, 5, 1 },
				{ 2, 8, 8, 7, 9, 9, 9, 3 },
				{ 4, 3, 2, 1, 0, },
				{ 1, 2, 5, 9, 10, }, };

		for (int i = 0; i < arrays.length; i++)
			valley_peak(arrays[i]);
	}

	static void valley_peak(int[] a)
	{

		int[] arrays = a;
		System.out.print("\narray: ");
		for (int i = 0; i < arrays.length; i++)
			System.out.print(arrays[i] + " ");

		System.out.println("");
		for (int i = 0; i < arrays.length; i++)
		{

			if (i == 0)
			{
				if (arrays[i] < arrays[i + 1])
				{
					valley(i, arrays[i]);
				} else if (arrays[i] > arrays[i + 1])
				{
					peak(i, arrays[i]);
				}
			} else if (i == (arrays.length - 1))
			{
				if (arrays[i] < arrays[i - 1])
				{
					valley(i, arrays[i]);
				} else if (arrays[i] > arrays[i - 1])
				{
					peak(i, arrays[i]);
				}
			} else
			{
				if (arrays[i] < arrays[i - 1] && arrays[i] < arrays[i + 1])
				{
					valley(i, arrays[i]);
				} else if (arrays[i] > arrays[i - 1] && arrays[i] > arrays[i + 1])
				{
					peak(i, arrays[i]);
				}
			}

		}
	}

	static void valley(int a, int b)
	{
		System.out.println("[" + a + "]=" + b + " is a valley");
	}

	static void peak(int a, int b)
	{
		System.out.println("[" + a + "]=" + b + " is a peak");
	}

}