package com.seneca.exceptions;


/**
 * 
 * @author Tim Roberts
 * @date 17-Feb-2020
 * 
 * Exception for when the user tries to deposit money
 * in a GIC account
 */
@SuppressWarnings("serial")
public class NotAvailableForGIC extends AccountExceptions {
	public NotAvailableForGIC(String s){
		super(s);
	}
}
