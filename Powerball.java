package com.gabeskorski.apps;

import java.util.Random;

public class Powerball
{
	private static final int MIN = 1;
	private static final int MAX_WHITE = 69;
	private static final int MAX_RED = 26;
	private static final int TOTAL_NUMBERS = 6;


	public static void main(String[] args)
	{
		int[] powerballNumbers = new int[TOTAL_NUMBERS];
		Random rand = new Random();
		for(int i = 1; i < TOTAL_NUMBERS; i++){
			powerballNumbers[i] = rand.nextInt(MAX_WHITE) + 1;
		}
		
		powerballNumbers[TOTAL_NUMBERS - 1] = rand.nextInt(MAX_RED) + 1;
		
		for (int i = 0; i < TOTAL_NUMBERS; i++){
			if (i == TOTAL_NUMBERS - 1) System.out.print("PB: ");
			System.out.print(powerballNumbers[i] + " ");
		}
	}

}
