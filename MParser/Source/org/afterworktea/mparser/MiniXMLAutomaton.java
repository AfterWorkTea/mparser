package org.afterworktea.mparser;

/**
 * @author Robert Berlinski
 * 
 *         The interface for Mini XML Automaton
 */
public interface MiniXMLAutomaton {

	/**
	 * @return the XML tree travers position state as int value
	 */
	public int getStateInt();

	/**
	 * @param element
	 *            the name of starting element
	 * @return the element's XML tree travers position
	 */
	public int startElement(String element);

	/**
	 * @param element
	 *            the name of an element
	 * @return the element's XML tree travers position
	 * @throws MiniXMLException
	 */
	public int endElement(String element) throws MiniXMLException;

	/**
	 * Resets the state to the root of document.
	 */
	public void reset();
}
