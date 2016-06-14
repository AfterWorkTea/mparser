package org.afterworktea.mparser;

import java.util.*;

/**
 * @author Robert Berlinski
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class MiniXMLAttrElement extends MiniXMLElement {

	private Map<String, String> attributes;

	public MiniXMLAttrElement(int state, String n) {
		super(state, n);
		attributes = new TreeMap<String, String>();
	}

	public boolean isEmpty() {
		return attributes.isEmpty();
	}

	public boolean containsKey(String key) {
		return attributes.containsKey(key);
	}

	public String get(String key) {
		return (String) attributes.get(key);
	}

	public String get(String key, String def) {
		if(attributes.containsKey(key))	{
			return (String) attributes.get(key);
		}
		return def;
	}

	public void put(String key, String value) {
		attributes.put(key, value);
	}

	public Iterator<String> getIterator() {
		Set<String> set = attributes.keySet();
		if (set == null)
			return null;
		return set.iterator();
	}

}
