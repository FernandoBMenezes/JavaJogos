package com.mondergames.Encrypts;

public class VDouble {

	private String value;

	/**
	 * @param d
	 *            set value
	 */
	public VDouble(double d) {
		this.setValue(d);
	}

	/**
	 * @return Double of decode value
	 */
	public double toDouble() {
		return getValue();
	}

	/**
	 * @return Int of decode value
	 */
	public int toInt() {
		return ((int) getValue());
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return Encrypt.double64decode(value);
	}

	/**
	 * @param v
	 *            the value to set
	 */
	public void setValue(double v) {
		this.value = Encrypt.double64encode(v);
	}
}
