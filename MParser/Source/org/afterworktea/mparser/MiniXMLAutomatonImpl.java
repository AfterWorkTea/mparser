package org.afterworktea.mparser;

import java.util.*;

/**
 * Insert the type's description here. Creation date: (11/5/2003 9:32:35 AM)
 * 
 * @author: Robert Berlinski
 */

public class MiniXMLAutomatonImpl implements MiniXMLAutomaton {

	private MiniXMLState oState;
	private ArrayList<String> tokens;
	private int nextState;
	private MiniXMLState oDoc;

	/**
	 * MiniXMLAutomaton constructor comment.
	 */
	public MiniXMLAutomatonImpl() {
		super();
		set();
	}

	public MiniXMLAutomatonImpl(String learn) {
		super();
		set();
		learn(learn);
	}

	private void set() {
		tokens = new ArrayList<String>();
		oDoc = new MiniXMLState();
		oState = oDoc;
		nextState = 0;
	}

	public void reset() {
		oState = oDoc;
	}

	private void checkAndStartElement(StringBuilder name) {
		if (name.length() > 0) {
			startElement(name.toString());
		}
	}

	public void learn(String learn) {
		char c;
		StringBuilder name = new StringBuilder("");
		for (int i = 0; i < learn.length(); i++) {
			c = learn.charAt(i);
			switch (c) {
			case '(':
				checkAndStartElement(name);
				name.delete(0, name.length());
				break;
			case ')':
				checkAndStartElement(name);
				name.delete(0, name.length());
				oState = oState.getParent();
				break;
			case '\n':
			case '\r':
			case '\t':
			case ' ':
				break;
			default:
				name.append(c);
				break;
			}
		}
	}

	public int startElement(String name) {
		oState = oState.getChild(nextState, getTokenID(name));
		int state = oState.getStateID();
		if (state == nextState)
			nextState++;
		return state;
	}

	public int endElement(String name) throws MiniXMLException {
		if (name == null)
			throw new MiniXMLException(MiniXMLException.MXMLE_AE_UKNOWN_END_TOKEN);
		if (name.compareToIgnoreCase(getName()) != 0)
			throw new MiniXMLException(MiniXMLException.MXMLE_AE_UKNOWN_END_TOKEN);
		int state = oState.getStateID();
		oState = oState.getParent();
		if (oState == null)
			throw new MiniXMLException(MiniXMLException.MXMLE_AE_UKNOWN_PARENT);
		return state;
	}

	private int getTokenID(String name) {
		if (!tokens.contains(name))
			tokens.add(name);
		return tokens.indexOf(name);
	}

	public int getToken() {
		return oState.getTokenID();
	}

	public int getStateInt() {
		if (oState == null)
			return 0;
		return oState.getStateID();
	}

	public MiniXMLState getState() {
		return oState;
	}

	public String getName() {
		return tokens.get(getToken());
	}

}