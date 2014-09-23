package org.afterworktea.mparser;

/**
 * @author Robert Berlinski
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public interface MiniXMLAutomaton {
	public int getStateInt();

	public int startElement(String element);

	public int endElement(String element) throws MiniXMLException;

	public void reset();
}
