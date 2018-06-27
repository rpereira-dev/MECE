package com.rpereira.mece;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A one-file-library which makes microsoft edge cookie extraction easier
 * 
 * @author rpereira
 *
 */
public class MECE {

	/**
	 * @return a hashmap, keys are the cookie's name, value is their value
	 */
	public static HashMap<String, String> extract() {
		HashMap<String, String> cookies = new HashMap<String, String>();
		String dirpath = "C:/Users/" + System.getProperty("user.name")
				+ "/AppData/Local/Packages/Microsoft.MicrosoftEdge_8wekyb3d8bbwe/AC/";
		extract(dirpath.replace('/', File.separatorChar), cookies);
		return (cookies);
	}

	/**
	 * 
	 * @param folders
	 *            : list of folder path on which the program should search for
	 *            cookies
	 * @return a hashmap, keys are the cookie's name, value is their value
	 */
	public static HashMap<String, String> extract(ArrayList<String> dirpaths) {
		HashMap<String, String> cookies = new HashMap<String, String>();
		for (String dirpath : dirpaths) {
			extract(dirpath, cookies);
		}
		return (cookies);
	}

	/**
	 * Extract cookies from 'dirpath'
	 * 
	 * @param dirpath
	 * @param cookies
	 * @return a hashmap, keys are the cookie's name, value is their value
	 */
	public static HashMap<String, String> extract(String dirpath) {
		HashMap<String, String> cookies = new HashMap<String, String>();
		extract(new File(dirpath), cookies);
		return (cookies);
	}

	/**
	 * Extract cookies from 'dirpath' and stores them into the 'cookies' HashMap
	 * 
	 * @param dirpath
	 * @param cookies
	 */
	public static void extract(String dirpath, HashMap<String, String> cookies) {
		extract(new File(dirpath), cookies);
	}

	/**
	 * Extract cookies from the file 'f' and stores them into the 'cookies' HashMap
	 * 
	 * @param f
	 *            : if 'f' is a directory, search inside it recursively for cookies
	 *            to extract, else if 'f' is a regular file (with '.cookie'
	 *            extension), open it and parse cookies
	 * @param cookies
	 *            : the map to which cookies are stored
	 */
	private static void extract(File f, HashMap<String, String> cookies) {
		if (!f.exists() || !f.canRead()) {
			System.out.println("Couldn't access directory " + f.getAbsolutePath());
			return;
		}

		if (f.isDirectory()) {
			for (File child : f.listFiles()) {
				extract(child, cookies);
			}
		} else if (f.isFile() && f.getName().endsWith(".cookie")) {
			System.out.println("Find a cookie, yummy : " + f.getAbsolutePath());
		}
	}
}
