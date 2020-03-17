package com.seneca.exceptions;




/**
 * 
 * @author Tim Roberts
 * @date 17-Feb-2020
 * 
 * Exception for when the user tries to withdraw 
 * more money then they have available.
 */
@SuppressWarnings("serial")
public class InsufficientFunds extends AccountExceptions {
	public InsufficientFunds(String s){
		super(s);
	}
}
