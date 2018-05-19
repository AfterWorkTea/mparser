package org.afterworktea.mparser;

/**
 * Insert the type's description here. Creation date: (11/7/2003 2:41:23 PM)
 * 
 * @author: Robert Berlinski
 */
public class MiniXMLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6310681830037961308L;
	// Unknown error
	public static final int MXMLE_XX_E_UKNOWN = 0x00;
	// Token Parser errors x01 - x20
	public static final int MXMLE_TP_E_IE = 0x01;
	// public static final int _MXMLE_TP_E_NameFirstChar = 0x01; //getName
	// !isFirstNameChar
	// Token Grammar errors x21 - x40
	public static final int MXMLE_TGE_UKNOWN_TOKEN = 0x21;
	public static final int MXMLE_TGE_UKNOWN_XML_TOKEN = 0x22;
	public static final int MXMLE_TGE_UKNOWN_START_TOKEN = 0x23;
	public static final int MXMLE_TGE_UKNOWN_END_TOKEN = 0x24;
	public static final int MXMLE_TGE_END_ELEMENT = 0x25;
	public static final int MXMLE_TGE_START_ELEMENT = 0x26;
	public static final int MXMLE_TGE_XML_ELEMENT = 0x27;
	// Automaton errors x41 - x61
	public static final int MXMLE_AE_UKNOWN_END_TOKEN = 0x61;
	public static final int MXMLE_AE_UKNOWN_PARENT = 0x62;
	// public static final int _MXMLE_AT_E = 0x41;

	private final int code;
	private final Exception reason;

	/**
	 * MiniXMLException constructor comment.
	 */
	/**
	 * public MiniXMLException() { super(); code = MXMLE_XX_E_UKNOWN; reason =
	 * null; }
	 */

	public MiniXMLException(int c) {
		super();
		code = c;
		reason = null;
	}

	/**
	 * MiniXMLException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public MiniXMLException(String s) {
		super(s);
		code = MXMLE_XX_E_UKNOWN;
		reason = null;
	}

	public MiniXMLException(int c, Exception e) {
		super(e.getMessage());
		code = c;
		reason = e;
	}

	public int getCode() {
		return code;
	}

	public Exception getReason() {
		return reason;
	}

}