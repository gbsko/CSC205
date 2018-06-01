/*Program: PasswordGiver
 *Description: Randomly generates the number of passwords the user wants from Password class. 
 *By: Gabe Skorski
 *Date: 10.13.2017
 */

package com.gabeskorski.apps;

import java.util.Random;
import java.util.Scanner;

public class PasswordGiver
{
	static final int MIN_LENGTH = 6;
	static final int MAX_LENGTH = 13;
	static final Random randy = new Random();
	static int numberOfPasswords;

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("How many passwords do you want? ");
		numberOfPasswords = input.nextInt();
		input.close();

		for (int i = 0; i < numberOfPasswords; i++){
		int length = randy.nextInt(MAX_LENGTH-MIN_LENGTH+1) + MIN_LENGTH;
		Password myPassword = new Password(length);
		System.out.println(myPassword.getPassword());
		}	
	}
}


