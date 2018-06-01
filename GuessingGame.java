package com.gabeskorski.apps;

/*
Program: GuessingGame
Description: a program that enables a user to play number guessing games. 
The following is a nutshell description of a number guessing game:

   + a random number is generated
   + loop prompting the user to enter guesses
     until the user guesses the numbers or hits
     hits the maximum number of allowed guesses
     or enters the "quit" sentinel value

By Gabe Skorski
09.14.2017
*/

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame
{
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 205;
	private static final int QUIT_VALUE = -1;
	private static final int MAX_GAMES = 4;
	private static final int MAX_GUESSES = 10;
	private static final int HINT_THRESHOLD = 5;
	private static final int BACKDOOR_VALUE = -314;

	private static final String NOPE_MSG = "nope...";
	private static final String NOPE_NOPE_MSG = "you've already guessed that wrong guess...";
	private static final String WINNER_MSG = "you're a winner... # of guesses: ";
	private static final String LOSER_MSG = "\ntoo many guesses entered... the number was ";
	private static final String QUITTER_MSG = "you're a quitter... the number was ";
	private static final String MAX_GAMES_PLAYED_MSG = "you've played the maximum number (" + MAX_GAMES + ") of games";
	private static final String PLAY_AGAIN_PROMPT = "\nDo you want to play again (n or y)? ";
	private static final String BOLD_BEGIN = "*** ";
	private static final String BOLD_END = " ***";
	private static final String PLAY_MSG = " playing the CSC" + MAX_NUMBER + " guessing game." + BOLD_END;
	private static final String WELCOME_MSG = BOLD_BEGIN + "Hello! Have fun" + PLAY_MSG;
	private static final String THANKS_MSG = BOLD_BEGIN + "Thanks for" + PLAY_MSG;
	private static final String PROMPT = "Enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER
			+ ", or -1 to QUIT: ";

	private static int[] guesses = new int[MAX_NUMBER];

	public static void main(String[] args)
	{
		int gamesPlayed = 0;
		int gamesWon = 0;
		int gamesLost = 0;
		int gamesQuit = 0;
		Boolean gameOn = true;
		System.out.println(WELCOME_MSG);

		do
		{
			Scanner input = new Scanner(System.in);
			Random randy = new Random();
			int randomNumber = randy.nextInt(205) + 1;

			int numberOfGuesses = 0;
			for (int i = 0; i < MAX_NUMBER; i++)
			{
				guesses[i] = 0;
			}

			boolean guessing = true;
			do
			{

				numberOfGuesses++;

				if (numberOfGuesses <= MAX_GUESSES)
				{

					int guess = getInput(input);

					addGuess(guess);

					if (checkGuess(guess))
					{
						System.out.println(NOPE_NOPE_MSG);
					} else if (guess == randomNumber)
					{
						gamesWon++;
						gamesPlayed++;
						System.out.println(WINNER_MSG + "" + numberOfGuesses);
						guessing = false;

					} else if (guess == QUIT_VALUE)
					{
						gamesPlayed++;
						gamesQuit++;
						guessing = false;
						System.out.println(QUITTER_MSG + "" + randomNumber);
					} else if (guess == BACKDOOR_VALUE)
					{
						System.out.println(randomNumber);
					} else if (numberOfGuesses > HINT_THRESHOLD)
					{
						if (guess < randomNumber)
						{
							System.out.println(NOPE_MSG + " Higher");
						} else
						{
							System.out.println(NOPE_MSG + " Lower");
						}
					} else
					{
						System.out.println(NOPE_MSG);
					}

				} else
				{
					gamesPlayed++;
					gamesLost++;
					System.out.println(LOSER_MSG + "" + randomNumber);
					guessing = false;
				}
			} while (guessing);

			if (gamesPlayed == MAX_GAMES)
			{
				gameOn = false;
				System.out.println(MAX_GAMES_PLAYED_MSG);
				break;
			}

			Boolean ask = true;
			do
			{

				System.out.println(PLAY_AGAIN_PROMPT);
				String answer = input.next();

				if (answer.equalsIgnoreCase("y"))
				{
					gameOn = true;
					ask = false;
				} else if (answer.equalsIgnoreCase("n"))
				{
					gameOn = false;
					ask = false;
				} else
				{
					System.out.println("WRONG INPUT");
				}
			} while (ask);

		} while (gameOn);
		float winPercent = ((float) gamesWon / (float) gamesPlayed) * 100;

		System.out.println(THANKS_MSG + "\n" + "\nGames played: " + gamesPlayed + " \nGames won: " + gamesWon
				+ " \nGames lost: " + gamesLost + " \nGames quit: " + gamesQuit + " \nWinning percentage: "
				+ (int) Math.floor(winPercent) + "%");
	}

	static int getInput(Scanner scanner)
	{
		final String ERR_MSG_PREFIX = "*** error: ";
		int n = 0;
		do
		{
			System.out.print("\n" + PROMPT);
			try
			{
				n = scanner.nextInt();
			} catch (InputMismatchException e)
			{
				System.out.println(ERR_MSG_PREFIX + "whole number input only");
				scanner.nextLine();
				continue;
			}
			if (n == QUIT_VALUE)
				return n;
			if (n == BACKDOOR_VALUE)
				return n;
			if (n < MIN_NUMBER)
				System.out.println(ERR_MSG_PREFIX + "input must be >= " + MIN_NUMBER);
			else if (n > MAX_NUMBER)
				System.out.println(ERR_MSG_PREFIX + "input must be <= " + MAX_NUMBER);
			else
				return n;
		} while (true);

	}

	public static void addGuess(int n)
	{
		if (n > 0)
			guesses[n] += 1;
	}

	public static boolean checkGuess(int n)
	{
		boolean dupe = true;
		if (n > 0 && guesses[n] > 1)
		{
			dupe = true;
		} else
		{
			dupe = false;
		}
		return dupe;
	}

}