package org.afterworktea.mparser;

import java.io.*;

/**
 * Insert the type's description here. Creation date: (8/13/2003 9:48:41 AM)
 * 
 * @author Robert Berlinski
 */
public class TokenStream {

	private Reader oReader;
	private int nextp0, nextp1, nextp2, nextp3, nextp4, nextp5;
	private static final int tError = -1;

	/**
	 * TokenStreem constructor comment.
	 */
	public TokenStream(InputStream is) throws IOException {
		super();
		oReader = new InputStreamReader(is);
		nextp0 = readOneChar();
		nextp1 = readOneChar();
		nextp2 = readOneChar();
		nextp3 = readOneChar();
		nextp4 = readOneChar();
		nextp5 = readOneChar();
	}

	public TokenStream(Reader r) throws IOException {
		super();
		oReader = r;
		nextp0 = readOneChar();
		nextp1 = readOneChar();
		nextp2 = readOneChar();
		nextp3 = readOneChar();
		nextp4 = readOneChar();
		nextp5 = readOneChar();
	}

	public int getNext() {
		int ret = nextp0;
		nextp0 = nextp1;
		nextp1 = nextp2;
		nextp2 = nextp3;
		nextp3 = nextp4;
		nextp4 = nextp5;
		nextp5 = readOneChar();
		return ret;
	}

	public String getString() {
		char c[] = { 0, 0, 0, 0, 0, 0 };
		c[0] = (nextp0 == tError) ? 0 : (char) nextp0;
		c[1] = (nextp1 == tError) ? 0 : (char) nextp1;
		c[2] = (nextp2 == tError) ? 0 : (char) nextp2;
		c[3] = (nextp3 == tError) ? 0 : (char) nextp3;
		c[4] = (nextp4 == tError) ? 0 : (char) nextp4;
		c[5] = (nextp5 == tError) ? 0 : (char) nextp5;
		String s = new String(c);
		// , (char) nextp1};
		return s;
	}

	private int readOneChar() {
		try {
			if (oReader.ready())
				return oReader.read();
			else
				return tError;
		} catch (IOException e) {
			return tError;
		}
	}

	public int testNext() {
		return nextp0;
	}

}
