/*
 Program: MakeHelloWorld
 Description: A program that prints the code for a basic "Hello World" application.
 By Gabe Skorski
 08.30.2017
 */

package com.gabeskorski.apps;

import java.text.SimpleDateFormat;
import java.util.*;;

public class MakeHelloWorld
{

	public static void main(String[] args)
	{
		
		//Gets the date formats it YYYYY/MM//dd
		Calendar today = Calendar.getInstance();
		SimpleDateFormat formating = new SimpleDateFormat("yyyyy/MM/dd");
		String formatted = formating.format(today.getTime());

		//Stores the comment heading for the program as a String
		String heading = ("/*" 
	+ "\n * This Java application prints the phrase hello, world followed by" 
    + "\n * a newline to the standard output stream. This program was program" 
    + "\n * generated."
    + "\n *"
    + "\n * @creator Gabe Skorski"
    + "\n * @generated " + formatted 
    + "\n */");
		
		//Stores the code body as a string
		String body = ("\npublic class HelloWorld" 
						+"\n{"
						+ "\n\tpublic static void main(String[] argv)" 
						+ "\n\t{"
						+"\n\t\tSystem.out.print(\"hello, world\\n\");"
						+"\n\t}"
						+"\n}");
		
		//Prints entire program code to the output stream
		System.out.print(heading + "\n" + body);
	}

}
