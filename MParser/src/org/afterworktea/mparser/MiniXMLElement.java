package org.afterworktea.mparser;

/**
 * @author Robert Berlinski
 * 
 *         The base class for Mini XML Elements
 */

public class MiniXMLElement {

	private int stateID;
	private String name;

	/**
	 * @param state
	 *            the XML tree travers position
	 * @param n
	 *            the name of the attribute
	 */
	public MiniXMLElement(int state, String n) {
		stateID = state;
		name = n;
	}

	public int getStateID() {
		return stateID;
	}

	public String getName() {
		return name;
	}

}
