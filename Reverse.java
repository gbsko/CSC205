/* Program: Reverse
 * Description: Gets a String from the user and prints it backwards.
 * By: Gabe Skorski
 * Date: 10.06.2017
 */

package com.gabeskorski.apps;

import java.util.Scanner;

public class Reverse
{
	public static void main(String[] args)
	{
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter a word or statement to be reversed.");
		char[] userString = userInput.nextLine().toCharArray();
		reverse(userString, 0, userString.length - 1);
		System.out.println(userString);
		userInput.close();
	}
	
	public static char[] reverse (char[] stringArray, int i, int j){
		if (i == j || i > j) return stringArray;
		char temp = stringArray[i];
		stringArray[i] = stringArray[j];
		stringArray[j] = temp;
		i++;
		j--;
		return reverse(stringArray, i ,j);
	}
}
