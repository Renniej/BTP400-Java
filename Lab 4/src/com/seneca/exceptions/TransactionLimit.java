package com.seneca.exceptions;


/**
 * 
 * @author Tim Roberts
 * @date 17-Feb-2020
 * 
 * Exception for when the transaction limit
 * of a Chequing account has been reached
 */
@SuppressWarnings("serial")
public class TransactionLimit extends AccountExceptions{
	public TransactionLimit(String s){
		super(s);
	}
}
