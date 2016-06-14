package org.afterworktea.mparser;

/**
 * @author Robert Berlinski
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class MiniXMLStartElement extends MiniXMLAttrElement {

	private boolean terminal;

	public MiniXMLStartElement(int state, String n) {
		super(state, n);
		terminal = false;
	}

	public boolean isTerminal() {
		return terminal;
	}

	public void setTerminal() {
		this.terminal = true;
	}

}
