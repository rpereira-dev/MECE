package com.rpereira.mece;

import java.util.HashMap;

public class Main {

	/**
	 * The main
	 * 
	 * @param args
	 *            : command line arguments
	 */
	public static void main(String[] args) {
		HashMap<String, String> cookies;
		if (args.length == 0) {
			cookies = MECE.extract();
			for (String key : cookies.keySet()) {
				System.out.println(key + " = " + cookies.get(key));
			}
		} else {
			for (String arg : args) {
				System.out.println(arg);
			}
		}
	}
}
