package org.afterworktea.mparser;

import java.util.*;

/**
 * @author Robert Berlinski
 * 
 *         The Attribute Class
 */
public class MiniXMLAttrElement extends MiniXMLElement {

	private Map<String, String> attributes;

	/**
	 * @param state
	 *            the XML tree travers position
	 * @param n
	 *            the name of the element
	 */
	public MiniXMLAttrElement(int state, String n) {
		super(state, n);
		attributes = new TreeMap<String, String>();
	}

	public boolean isEmpty() {
		return attributes.isEmpty();
	}

	/**
	 * @param key
	 *            a name of an attribue
	 * @return true if contains the key
	 */
	public boolean containsKey(String key) {
		return attributes.containsKey(key);
	}

	/**
	 * @param key
	 *            a name of an attribute
	 * @return the value of attribute to which the specified key is mapped, or
	 *         null
	 */
	public String get(String key) {
		return attributes.get(key);
	}

	/**
	 * @param key
	 *            a name of an attribute
	 * @param def
	 *            a defult reeturn value
	 * @return the value of attribute to which the specified key is mapped, or
	 *         def
	 */
	public String get(String key, String def) {
		if (attributes.containsKey(key)) {
			return attributes.get(key);
		}
		return def;
	}

	/**
	 * @param key
	 *            the key to be added
	 * @param value
	 *            the value to be added
	 */
	public void put(String key, String value) {
		attributes.put(key, value);
	}

	/**
	 * @return the iterator for the map of attributes
	 */
	public Iterator<String> getIterator() {
		Set<String> set = attributes.keySet();
		if (set == null)
			return null;
		return set.iterator();
	}

	/**
	 * @return the map of attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

}
