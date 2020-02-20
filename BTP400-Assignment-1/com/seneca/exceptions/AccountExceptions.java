package com.seneca.exceptions;



/**
 * 
 * @author Tim Roberts
 * @date 17-Feb-2020
 * 
 * Exception that acts as a SuperClass 
 * to use with throws keyword
 */
@SuppressWarnings("serial")
public class AccountExceptions extends Exception{
	public AccountExceptions(String s){
		super(s);
	}
}