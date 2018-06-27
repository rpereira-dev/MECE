package com.rpereira.mece;

/**
 * Represent a cookie
 * 
 * @author rpereira
 *
 */
public class Cookie {

	public final String name;
	public final String value;
	public final String referer;

	public Cookie(String name, String value, String referer) {
		this.name = name;
		this.value = value;
		this.referer = referer;
	}
}
