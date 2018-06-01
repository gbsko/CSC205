package com.gabeskorski.apps;

/*
Program: BitOperators
Description: Write a Java application class TestBitOperators that implements the interface BitOperators.
By Gabe Skorski
09.28.2017
*/

import java.util.InputMismatchException;
import java.util.Scanner;


interface BitOperators {
    BitOperators and(byte a, byte b);
    BitOperators or(byte a, byte b);
    BitOperators xor(byte a, byte b);
    BitOperators shift(byte n, byte l, byte r);
    BitOperators comp(byte n);
 }


public class TestBitOperators implements BitOperators {
	
	static final byte MAX = 127;
	static final byte MIN = -128;
	static final byte MAX2 = 8;
	static final byte MIN2 = 0;
	static final String PROMPTMAIN = "enter a and b numbers in the interval [" + MIN + ", " + MAX + "] (-1 -1 to exit): ";
	static final String PROMPTB = "enter b number in the interval [" + MIN + ", " + MAX + "] (-1 -1 to exit): ";

	static final String PROMPT2 = "enter #left-shift bits in the interval [" + MIN2 + ", " + MAX2 + "]: ";
	static final String PROMPT3 = "enter #right-shift bits in the interval [" + MIN2 + ", " + MAX2 + "]: ";
			
	public static void main(String[] argv) {

		Scanner input = new Scanner(System.in);
		byte a, b, l, r;
		boolean looping = true;
		
		while(looping){

		System.out.println(PROMPTMAIN);
		a = getInput(input, PROMPTMAIN, MAX, MIN);
		b = getInput(input, PROMPTB, MAX, MIN);
		
		if (a == -1 && b == -1)
			break;
		System.out.println(PROMPT2);	 
		l = getInput(input, PROMPT2, MAX2, MIN2);
		System.out.println(PROMPT3);
		r = getInput(input, PROMPT3, MAX2, MIN2);
			 
	    new TestBitOperators().and(a,b).or(a,b).xor(a,b).shift(a,l,r).comp(a);
		}
	 }





	 
		public BitOperators and(byte a, byte b)
		{
			int c = a&b;
			System.out.println(a + " AND " + b + " is " + c);
			return this;
		}

		
		public BitOperators or(byte a, byte b)
		{
			int c = a|b;
			System.out.println(a + " OR " + b + " is " + c);
			return this;
		}

		
		public BitOperators xor(byte a, byte b)
		{
			int c = a^b;
			System.out.println(a + " XOR " + b + " is " + c);
			return this;
		}

		
		public BitOperators shift(byte n, byte l, byte r)
		{
			int c = n<<l;
			int d = n>>r;
			int e = n >>>r;
			System.out.println(n + " shifted left " + l + " bits is " + c);
			System.out.println(n + " shifted right " + r + " bits is " + d);
			System.out.println(n + " unsigned-shifted right " + r + " bits is " + e);
			return this;
		}

		
		public BitOperators comp(byte n)
		{
			int c = ~n;
			System.out.println(n + " COMPLEMENT is " + c);
			return this;
		}
		
		static byte getInput(Scanner scanner, String prompt, byte max, byte min)
		{

			final String ERR_MSG_PREFIX = "*** ERROR: ";
			byte n = 0;

			do
			{
				try
				{
					n = scanner.nextByte();
					
				} catch (InputMismatchException e)
				{
					System.out.println(ERR_MSG_PREFIX + "WRONG INPUT ***");
					scanner.nextLine();
					System.out.print(prompt);
					continue;
				}
				if (n < min)
					System.out.println(ERR_MSG_PREFIX + "input must be >= " + min);
				else if (n > max)
					System.out.println(ERR_MSG_PREFIX + "input must be <= " + max);
				else
					return n;
			} while (true);

		}
		



	}