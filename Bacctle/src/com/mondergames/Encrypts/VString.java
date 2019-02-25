package com.mondergames.Encrypts;

public class VString {

	private String value;

	/**
	 * @param v
	 *            set value
	 */
	public VString(String v) {
		this.setValue(v);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return Encrypt.base64decode(this.value);
	}

	/**
	 * @param v
	 *            the value to set
	 */
	public void setValue(String v) {
		this.value = Encrypt.base64encode(v);
	}
}
