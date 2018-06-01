/*Program: PasswordGiver
 *Description: Password class for PasswordGiver program. 
 *By: Gabe Skorski
 *Date: 10.13.2017
 */

package com.gabeskorski.apps;

import java.util.Random;

public class Password {
	
	private int passwordLength;
	private char[] passwordArray;
	private boolean hasCapital = false;
	private boolean hasPunctuation = false;
	private boolean hasNumber = false;
	private boolean isGood = false;
	private static Random rander = new Random();

	
	
	public Password(int a){
		passwordLength = a;
		passwordArray = new char [passwordLength];
		setPassword();
	}
	
	public void setPassword(){
		for(int i = 0; i < passwordLength; i ++){
			passwordArray[i] = (char) (rander.nextInt(126-33) + 33);
		}
		if (checkPassword(passwordArray) == false){
			setPassword();
		}
	}
	
	
	private boolean checkPassword(char [] a){		
		if(number(a) && capital(a) && punctuation(a)){
			isGood = true;
		} else {
			isGood = false;
		}
		return isGood;
	}
	
	private boolean number(char[] a){
		for (int i = 0; i < passwordLength; i++){
			if ((int)a[i] >= 48 && (int)a[i] <= 57){
				hasNumber = true;
				break;
				}
			else hasNumber = false;
			}
		return hasNumber;
	}
	
	private boolean capital(char[] a){
		for (int i = 0; i < passwordLength; i ++){
			if ((int)(a[i]) >= 65 && (int)(a[i]) <= 90){
				hasCapital = true;
				break;
			}
			else hasCapital = false;
		}
		return hasCapital;
	}
	
	private boolean punctuation(char[] a){
		for (int i = 0; i < passwordLength; i ++){
			if (((int)(a[i]) >= 33 && (int)(a[i]) <= 47) || 
				((int)(a[i]) >= 58 && (int)(a[i]) <= 64) ||
				((int)(a[i]) >= 91 && (int)(a[i]) <= 96) ||
				((int)(a[i]) >= 123 && (int)(a[i]) <= 126)){
				hasPunctuation = true;
				break;
			}
			else hasPunctuation = false;
	}
		return hasPunctuation;
	}

	
	
	public String getPassword(){
		String passwordString = new String(passwordArray);
		return passwordString;
	}
	
}