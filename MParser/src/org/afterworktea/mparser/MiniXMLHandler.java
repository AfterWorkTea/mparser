package org.afterworktea.mparser;

/**
 * @author Robert Berlinski
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public interface MiniXMLHandler {
	public void startDocument();

	public void endDocument(int state);

	public void startElement(MiniXMLStartElement element);

	public void endElement(MiniXMLEndElement element);

	public void xmlElement(MiniXMLAttrElement element);

	public void data(int state, String data);
}
