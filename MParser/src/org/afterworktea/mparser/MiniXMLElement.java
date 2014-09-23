package org.afterworktea.mparser;

/**
 * @author Robert Berlinski
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */

public class MiniXMLElement {

	private int stateID;
	private String name;

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
