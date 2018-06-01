/*Program: CountingSort
 *Description: This program sorts an array of bytes from smallest to largest via a counting sort method.
 *By: Gabe Skorski
 *Date: 11.25.2017
 */

package com.gabeskorski.apps;

/*
 * This program implements a Counting Sort that sorts an array of 
 * byte values that are in the [Byte.MIN_VALUE,Byte.MAX_VALUE] interval.
 *
 * @created 02017.04.14
 */

public class CountingSort
{
	public static void main(String[] argv)
	{

		final byte[] A =
		{ 7, -128, 0, 7, -7, 127, 0, 7, -128, 7, 42, -42 };

		int[] a = new int[A.length];
		for (int i = 0; i < A.length; i++)
		{
			a[i] = A[i] + 128;
		}

		int[] counts = new int[256];

		for (int i = 0; i < counts.length; i++)
		{
			counts[i] = 0;
		}

		for (int i = 0; i < a.length; i++)
		{
			counts[a[i]]++;
		}

		int index = 0;
		for (int i = 0; i < counts.length; i++)
		{
			if (counts[i] > 0)
			{
				for (int j = 0; j < counts[i]; j++)
				{
					a[index] = i;
					index++;
				}
			}
		}

		for (int i = 0; i < a.length; i++)
		{
			a[i] = a[i] - 128;
		}

		for (int i = 0; i < A.length; i++)
		{
			if(i == 0) System.out.print("unsorted: ");
			System.out.print(A[i] + " ");
		}

		System.out.println("");

		for (int i = 0; i < a.length; i++)
		{
			if(i == 0) System.out.print("sorted: ");
			System.out.print(a[i] + " ");
		}

	}
}