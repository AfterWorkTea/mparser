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
	private static final int TERROR = -1;

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
		char[] c = { 0, 0, 0, 0, 0, 0 };
		c[0] = (nextp0 == TERROR) ? 0 : (char) nextp0;
		c[1] = (nextp1 == TERROR) ? 0 : (char) nextp1;
		c[2] = (nextp2 == TERROR) ? 0 : (char) nextp2;
		c[3] = (nextp3 == TERROR) ? 0 : (char) nextp3;
		c[4] = (nextp4 == TERROR) ? 0 : (char) nextp4;
		c[5] = (nextp5 == TERROR) ? 0 : (char) nextp5;
		return new String(c);
	}

	private int readOneChar() {
		try {
			if (oReader.ready())
				return oReader.read();
			else
				return TERROR;
		} catch (IOException e) {
			return TERROR;
		}
	}

	public int testNext() {
		return nextp0;
	}

}
