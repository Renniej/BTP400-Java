package com.seneca.exceptions;



/**
 * 
 * @author Tim Roberts
 * @date 17-Feb-2020
 * 
 * Exception for when the user tries to deposit or withdraw
 * a negative amount of money
 */
@SuppressWarnings("serial")
public class MustBePositive extends AccountExceptions {
	public MustBePositive(String s){
		super(s);
	}
}
