package com.rpereira.mece;

import java.util.ArrayList;

public class Main {

	/**
	 * The main
	 * 
	 * @param args
	 *            : command line arguments
	 */
	public static void main(String[] args) {
		ArrayList<Cookie> cookies = (args.length == 0) ? MECE.extract() : MECE.extract(args);
		
		System.out.println("name;value;referer");
		for (Cookie cookie : cookies) {
			System.out.println(cookie.name + ";" + cookie.value + ";" + cookie.referer);
		}
	}
}
