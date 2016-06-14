package org.afterworktea.mparser;

import java.io.*;

/**
 * @author Robert Berlinski
 */

public class MiniXMLParser {

	// private InputStream oInputStream;
	// private boolean bInsideTag;

	private MiniXMLTokenParser oTokenParser = null;
	private MiniXMLAutomaton automaton = null;
	private MiniXMLHandler handler = null;

	/**
	 * TokenParser constructor comment.
	 */
	public MiniXMLParser(MiniXMLHandler h, MiniXMLAutomaton a)
			throws IOException, MiniXMLException {
		super();
		// bInsideTag = false;
		automaton = a;
		handler = h;
	}

	public synchronized void parse(InputStream is) throws MiniXMLException {
		automaton.reset();
		try {
			oTokenParser = new MiniXMLTokenParser(is);
		} catch (IOException e) {
			throw new MiniXMLException(MiniXMLException._MXMLE_TP_E_IE);
		}
		MiniXMLToken oMiniXMLToken;
		boolean bEnd = false;
		handler.startDocument();
		String data;
		do {
			oMiniXMLToken = oTokenParser.getToken();
			switch (oMiniXMLToken.getType()) {
			case MiniXMLTokenParser.tError:
				bEnd = true;
				break;
			case MiniXMLTokenParser.tComment:
				break;
			case MiniXMLTokenParser.tCharData:
				data = oMiniXMLToken.getValue().trim();
				if (data.length() > 0)
					handler.data(automaton.getStateInt(), data);
				// System.out.println("{" + oMiniXMLToken.getValue().trim() +
				// "}");
				break;
			case MiniXMLTokenParser.tTagBeg:
				getStartElement(oMiniXMLToken.getValue());
				break;
			case MiniXMLTokenParser.tEndTagBeg:
				getEndElement(oMiniXMLToken.getValue());
				break;
			case MiniXMLTokenParser.tXMLTagBeg:
				getXMLElement();
				break;
			// case MiniXMLTokenParser.tXMLTagEnd:
			// System.out.print("?>");
			// break;
			default:
				throw new MiniXMLException(
						MiniXMLException._MXMLE_TGE_UKNOWN_TOKEN);
			}
		} while (!bEnd);
		handler.endDocument(automaton.getStateInt());
	}

	private void getXMLElement() throws MiniXMLException {
		MiniXMLToken oMiniXMLToken;
		boolean bEnd = false;
		String attrName, attrValue;
		MiniXMLAttrElement element = new MiniXMLAttrElement(0, "?xml");
		// System.out.print("<?xml");
		do {
			oMiniXMLToken = oTokenParser.getToken();
			switch (oMiniXMLToken.getType()) {
			case MiniXMLTokenParser.tError:
				throw new MiniXMLException(
						MiniXMLException._MXMLE_TGE_XML_ELEMENT);
			case MiniXMLTokenParser.tAttrName:
				attrName = oMiniXMLToken.getValue();
				oMiniXMLToken = oTokenParser.getToken();
				if (oMiniXMLToken.getType() == MiniXMLTokenParser.tAttrValue) {
					attrValue = oMiniXMLToken.getValue();
					element.put(attrName, attrValue);
					// System.out.print(" " + attrName + "=" + attrValue);
				} else
					bEnd = true;
				break;
			case MiniXMLTokenParser.tXMLTagEnd:
				handler.xmlElement(element);
				// System.out.print("?>" + oMiniXMLToken.getValue());
				bEnd = true;
				break;
			default:
				throw new MiniXMLException(
						MiniXMLException._MXMLE_TGE_UKNOWN_XML_TOKEN);
			}
		} while (!bEnd);
	}

	private void getStartElement(String name) throws MiniXMLException {
		MiniXMLToken oMiniXMLToken;
		boolean bEnd = false;
		String attrName, attrValue;
		automaton.startElement(name);
		MiniXMLStartElement element = new MiniXMLStartElement(
				automaton.getStateInt(), name);
		// System.out.print("<" + name);
		do {
			oMiniXMLToken = oTokenParser.getToken();
			switch (oMiniXMLToken.getType()) {
			case MiniXMLTokenParser.tError:
				throw new MiniXMLException(
						MiniXMLException._MXMLE_TGE_START_ELEMENT);
			case MiniXMLTokenParser.tAttrName:
				attrName = oMiniXMLToken.getValue();
				oMiniXMLToken = oTokenParser.getToken();
				if (oMiniXMLToken.getType() == MiniXMLTokenParser.tAttrValue) {
					attrValue = oMiniXMLToken.getValue();
					element.put(attrName, attrValue);
					// System.out.print(" " + attrName + "=" + attrValue);
				} else
					bEnd = true;
				break;
			case MiniXMLTokenParser.tTagEnd:
				handler.startElement(element);
				// System.out.print(">" + oMiniXMLToken.getValue());
				bEnd = true;
				break;
			case MiniXMLTokenParser.tEmptyTagEnd:
				element.setTerminal();
				handler.startElement(element);
				handler.endElement(new MiniXMLEndElement(element.getStateID(),
						name));
				// System.out.print("/>" + oMiniXMLToken.getValue());
				bEnd = true;
				automaton.endElement(name);
				break;
			default:
				throw new MiniXMLException(
						MiniXMLException._MXMLE_TGE_UKNOWN_START_TOKEN);
			}
		} while (!bEnd);
	}

	private void getEndElement(String name) throws MiniXMLException {
		MiniXMLToken oMiniXMLToken;
		boolean bEnd = false;
		// String attrName, attrValue;
		MiniXMLEndElement element = new MiniXMLEndElement(
				automaton.getStateInt(), name);
		automaton.endElement(name);
		// System.out.print("</" + name);
		do {
			oMiniXMLToken = oTokenParser.getToken();
			switch (oMiniXMLToken.getType()) {
			case MiniXMLTokenParser.tError:
				throw new MiniXMLException(
						MiniXMLException._MXMLE_TGE_END_ELEMENT);
			case MiniXMLTokenParser.tTagEnd:
				handler.endElement(element);
				// System.out.print(">" + oMiniXMLToken.getValue());
				bEnd = true;
				break;
			default:
				throw new MiniXMLException(
						MiniXMLException._MXMLE_TGE_UKNOWN_END_TOKEN);
			}
		} while (!bEnd);
	}

}