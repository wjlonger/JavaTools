package com.coder.util;

import java.io.*;

public class Base64DecodingException extends IOException {

	private char c;

	public Base64DecodingException(String message, char c){
		super(message);
		this.c = c;
	}

	public char getChar(){
		return c;
	}

}
