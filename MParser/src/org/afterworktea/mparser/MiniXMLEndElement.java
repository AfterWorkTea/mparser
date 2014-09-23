package org.afterworktea.mparser;

/**
 * @author Robert Berlinski
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class MiniXMLEndElement extends MiniXMLElement {

	private String data;

	public MiniXMLEndElement(int state, String n) {
		super(state, n);
	}

	public void setData(String s) {
		data = s;
	}

	public String getData() {
		return data;
	}

}
