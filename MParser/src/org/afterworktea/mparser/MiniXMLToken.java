package org.afterworktea.mparser;

/**
 * Insert the type's description here. Creation date: (7/28/2003 3:22:58 PM)
 * 
 * @author Robert Berlinski
 */
public class MiniXMLToken {

	private StringBuffer sb;
	private int type;

	/**
	 * MiniXMLToken constructor comment.
	 * 
	 * @param is
	 *            java.io.InputStream
	 * @exception java.io.IOException
	 *                The exception description.
	 */
	public MiniXMLToken() {
		super();
		sb = new StringBuffer();
		type = MiniXMLTokenParser.tError;
	}

	public void setType(int t) {
		type = t;
	}

	public void setValue(String s) {
		sb = new StringBuffer(s);
	}

	public void setValue(int i) {
		sb = new StringBuffer();
		append(i);
	}

	public int getType() {
		return type;
	}

	public String getValue() {
		return sb.toString();
	}

	public int getFirstChar() {
		if (sb.length() == 0)
			return MiniXMLTokenParser.tError;
		return (int) sb.charAt(0);
	}

	public void append(int i) {
		sb.append((char) i);
	}

}