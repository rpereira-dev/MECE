package com.rpereira.mece;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A one-file-library which makes microsoft edge cookie extraction easier
 * 
 * @author rpereira
 *
 */
public class MECE {

	/**
	 * Extract cookie from the user folder:
	 * "/AppData/Local/Packages/Microsoft.MicrosoftEdge_8wekyb3d8bbwe/AC/"
	 * 
	 * @return a list of cookie
	 */
	public static ArrayList<Cookie> extract() {
		ArrayList<Cookie> cookies = new ArrayList<Cookie>();
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
	 * @return a list of cookie
	 */
	public static ArrayList<Cookie> extract(String... dirpaths) {
		ArrayList<Cookie> cookies = new ArrayList<Cookie>();
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
	 * @return a list of cookie
	 */
	public static ArrayList<Cookie> extract(String dirpath) {
		ArrayList<Cookie> cookies = new ArrayList<Cookie>();
		extract(new File(dirpath), cookies);
		return (cookies);
	}

	/**
	 * Extract cookies from 'dirpath' and stores them into the 'cookies' list
	 * 
	 * @param dirpath
	 * @param cookies
	 */
	public static void extract(String dirpath, List<Cookie> cookies) {
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
	@SuppressWarnings("resource")
	private static void extract(File f, List<Cookie> cookies) {
		if (!f.exists() || !f.canRead()) {
			System.out.println("Couldn't access directory " + f.getAbsolutePath());
			return;
		}

		if (f.isDirectory()) {
			for (File child : f.listFiles()) {
				extract(child, cookies);
			}
		} else if (f.isFile() && f.getName().endsWith(".cookie")) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line;
				while ((line = br.readLine()) != null) {
					String name = line;
					String value = br.readLine();
					String referer = br.readLine();
					Cookie cookie = new Cookie(name, value, referer);
					cookies.add(cookie);

					while ((line = br.readLine()) != null && !line.equals("*"))
						;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
