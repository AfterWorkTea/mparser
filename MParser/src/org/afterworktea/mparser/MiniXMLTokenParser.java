package org.afterworktea.mparser;

import java.io.*;

/**
 * Insert the type's description here. Creation date: (7/21/2003 3:30:41 PM)
 * 
 * @author Robert Berlinski
 */
public class MiniXMLTokenParser {

	public final static int tError = -1;
	public final static int tTagBeg = 1;
	public final static int tEndTagBeg = 2;
	public final static int tTagEnd = 3;
	public final static int tEmptyTagEnd = 4;
	public final static int tAttrName = 5;
	public final static int tAttrValue = 6;
	public final static int tCharData = 7;
	public final static int tComment = 8;
	public final static int tXMLTagBeg = 9;
	public final static int tXMLTagEnd = 10;

	// special chars
	public final static int _gt = 1000;
	public final static int _lt = 1001;
	public final static int _amp = 1002;
	public final static int _apos = 1003;
	public final static int _quot = 1004;
	public final static int _letter = 1005;
	public final static int _name = 1006;

	// Class
	private boolean bInsideTag;
	private TokenStream oTokenStream;

	/**
	 * MinorTokenParser constructor comment.
	 */
	public MiniXMLTokenParser(InputStream is) throws IOException {
		super();
		oTokenStream = new TokenStream(is);
		bInsideTag = false;
	}

	private int getNext() {
		return oTokenStream.getNext();
	}

	private String getString() {
		return oTokenStream.getString();
	}

	public MiniXMLToken getToken() {
		MiniXMLToken oMiniXMLToken = new MiniXMLToken();
		oMiniXMLToken.setType(tError);

		if (!bInsideTag)
			return getTokenOutsideTag(oMiniXMLToken);
		else
			return getTokenInsideTag(oMiniXMLToken);
	}

	public MiniXMLToken getTokenOutsideTag(MiniXMLToken oMiniXMLToken) {
		int token = unEscapeCharDataWithEnd(testNext());
		if (token == tError)
			return oMiniXMLToken;
		if (token == '<') {
			getNext();
			switch (testNext()) {
			case tError:
				return oMiniXMLToken;
			case '/':
				getNext();
				return getTokenTagName(oMiniXMLToken, tEndTagBeg);
			case '!':
				oMiniXMLToken.append('<');
				return getTokenComment(oMiniXMLToken);
			case '?':
				if (getString().startsWith("?xml")) {
					bInsideTag = true;
					getNext();
					getNext();
					getNext();
					getNext();
					oMiniXMLToken.setType(tXMLTagBeg);
				}
				return oMiniXMLToken;
			default:
				return getTokenTagName(oMiniXMLToken, tTagBeg);
			}
		}
		while (true) {
			switch (unEscapeCharDataWithEnd(testNext())) {
			case tError:
				return oMiniXMLToken;
			case '<':
				oMiniXMLToken.setType(tCharData);
				return oMiniXMLToken;
			case _gt:
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('>');
				break;
			case _lt:
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('<');
				break;
			case _amp:
				getNext();
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('&');
				break;
			case _apos:
				getNext();
				getNext();
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('\'');
				break;
			case _quot:
				getNext();
				getNext();
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('"');
				break;
			default:
				oMiniXMLToken.append(getNext());
			}
		}
	}

	public MiniXMLToken getTokenInsideTag(MiniXMLToken oMiniXMLToken) {
		int token = getNext();
		// remove spaces
		if (isSpace(token))
			do {
				token = getNext();
			} while (isSpace(token) && token != tError);
		switch (token) {
		case tError:
			return oMiniXMLToken;
			// check for tTagEnd
		case '>':
			bInsideTag = false;
			oMiniXMLToken.setType(tTagEnd);
			return oMiniXMLToken;
			// check for tEmptyTagEnd
		case '/':
			if (testNext() == '>') {
				getNext();
				bInsideTag = false;
				oMiniXMLToken.setType(tEmptyTagEnd);
				return oMiniXMLToken;
			}
			return oMiniXMLToken;
		case '?':
			if (testNext() == '>') {
				getNext();
				bInsideTag = false;
				oMiniXMLToken.setType(tXMLTagEnd);
				return oMiniXMLToken;
			}
			return oMiniXMLToken;
		case '=':
			while (isSpace(testNext()))
				getNext();
			return getTokenAttributeValue(oMiniXMLToken);
		default:
			if (!isFirstNameChar(token))
				return oMiniXMLToken;
			else
				oMiniXMLToken.append(token);
			return getTokenAttributeName(oMiniXMLToken);
		}
	}

	public MiniXMLToken getTokenComment(MiniXMLToken oMiniXMLToken) {
		if (!getString().startsWith("!--"))
			return oMiniXMLToken;
		oMiniXMLToken.append(getNext());
		while (testNext() != '-' || testNext() != tError) {
			oMiniXMLToken.append(getNext());
			if (oMiniXMLToken.getValue().endsWith("-->")) {
				oMiniXMLToken.setType(tComment);
				return oMiniXMLToken;
			}
		}
		return oMiniXMLToken;
	}

	public MiniXMLToken getTokenAttributeValue(MiniXMLToken oMiniXMLToken) {
		int token, first = getNext();
		if (first != '\"' && first != '\'')
			return oMiniXMLToken;
		// else
		// oMiniXMLToken.append(first);
		while (true) {
			switch (unEscapeCharData(testNext())) {
			case tError:
				return oMiniXMLToken;
			case '\"':
			case '\'':
				token = getNext();
				oMiniXMLToken.setType(tAttrValue);
				if (token == first)
					return oMiniXMLToken;
				else
					oMiniXMLToken.append(token);
				break;
			case _gt:
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('>');
				break;
			case _lt:
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('<');
				break;
			case _amp:
				getNext();
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('&');
				break;
			case _apos:
				getNext();
				getNext();
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('\'');
				break;
			case _quot:
				getNext();
				getNext();
				getNext();
				getNext();
				getNext();
				oMiniXMLToken.append('"');
				break;
			default:
				oMiniXMLToken.append(getNext());
			}
		}
	}

	public boolean isChar(int token) {
		return (token == 0x09 || token == 0x0A || token == 0x0D || token >= 0x20
				&& token <= 0xFF);
	}

	public boolean isCharData(int token) {
		return (token != '<' && token != '&');
	}

	public boolean isDigit(int token) {
		return (token >= 0x30 && token <= 0x39);
	}

	public boolean isFirstNameChar(int token) {
		return (isLetter(token) || token == '_' || token == ':');
	}

	public boolean isLetter(int token) {
		return (token >= 0x41 && token <= 0x5A || token >= 0x61
				&& token <= 0x7A || token >= 0xC0 && token <= 0xD6
				|| token >= 0xD8 && token <= 0xF6 || token >= 0xF8
				&& token <= 0xFF);
	}

	public boolean isNameChar(int token) {
		return (isLetter(token) || isDigit(token) || token == '.'
				|| token == '-' || token == '_' || token == ':');
	}

	public boolean isSpace(int token) {
		return (token == 0x20 || token == 0x0A || token == 0x0D || token == 0x09);
	}

	public int testNext() {
		return oTokenStream.testNext();
	}

	private int unEscapeCharData(int token) {
		if (token == '<')
			return tError;
		if (token != '&')
			return token;
		getNext();
		String sToken = getString();
		if (sToken.startsWith("gt;"))
			return _gt;
		if (sToken.startsWith("lt;"))
			return _lt;
		if (sToken.startsWith("amp;"))
			return _amp;
		if (sToken.startsWith("apos;"))
			return _apos;
		if (sToken.startsWith("quot;"))
			return _quot;
		return tError;
	}

	private int unEscapeCharDataWithEnd(int token) {
		if (token != '&')
			return token;
		getNext();
		String sToken = getString();
		if (sToken.startsWith("gt;"))
			return _gt;
		if (sToken.startsWith("lt;"))
			return _lt;
		if (sToken.startsWith("amp;"))
			return _amp;
		if (sToken.startsWith("apos;"))
			return _apos;
		if (sToken.startsWith("quot;"))
			return _quot;
		return tError;
	}

	public MiniXMLToken getTokenTagName(MiniXMLToken oMiniXMLToken, int type) {
		bInsideTag = true;
		int token = getNext();
		oMiniXMLToken.setValue(token);
		if (isFirstNameChar(token)) {
			oMiniXMLToken.setType(type);
			while (true) {
				if (!isNameChar(testNext()))
					return oMiniXMLToken;
				oMiniXMLToken.append(getNext());
			}
		}
		return oMiniXMLToken;
	}

	public MiniXMLToken getTokenAttributeName(MiniXMLToken oMiniXMLToken) {
		oMiniXMLToken.setType(tAttrName);
		while (true) {
			if (!isNameChar(testNext()))
				return oMiniXMLToken;
			oMiniXMLToken.append(getNext());
		}
	}

}