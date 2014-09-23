package org.afterworktea.mparser;

import java.util.*;

/**
 * Insert the type's description here. Creation date: (11/5/2003 9:33:52 AM)
 * 
 * @author Robert Berlinski
 */
public class MiniXMLState {

	private int stateID;
	private int tokenID;
	private MiniXMLState parent;
	private Map<Integer, MiniXMLState> children;

	/**
	 * MiniXMLParserState constructor comment.
	 */
	public MiniXMLState() {
		super();
		set(0, 0, null);
	}

	public MiniXMLState(int state, int token) {
		super();
		set(state, token, null);
	}

	public MiniXMLState(int state, int token, MiniXMLState p) {
		super();
		set(state, token, p);
	}

	private void set(int state, int token, MiniXMLState p) {
		stateID = state;
		tokenID = token;
		parent = p;
		children = new TreeMap<Integer, MiniXMLState>();
	}

	public MiniXMLState getParent() {
		return parent;
	}

	public int getStateID() {
		return stateID;
	}

	public int getTokenID() {
		return tokenID;
	}

	public MiniXMLState getChild(int state, int token) {
		Integer oToken = new Integer(token);
		if (!children.containsKey(oToken)) {
			MiniXMLState child = new MiniXMLState(state, token, this);
			children.put(oToken, child);
		}
		return (MiniXMLState) children.get(oToken);
	}

	public boolean hasParen() {
		return (parent != null);
	}

}