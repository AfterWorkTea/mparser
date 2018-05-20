package org.afterworktea.mparser.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.afterworktea.mparser.MiniXMLAutomaton;
import org.afterworktea.mparser.MiniXMLAutomatonImpl;
import org.afterworktea.mparser.MiniXMLException;
import org.afterworktea.mparser.MiniXMLParser;

public class Example {

	private final static String XML_AUTOMATON_STATES = "parcel(sender(name())addressee(name()city()))";
	private final static String EXAMPLE_FILE_NAME = "parcel.xml";

	public Parcel parse(String fileName) throws IOException, MiniXMLException {
		File file = new File(fileName);
		InputStream is = new FileInputStream(file);
		MiniXMLAutomaton automaton = new MiniXMLAutomatonImpl(XML_AUTOMATON_STATES);
		ParcelMiniXMLHandler handler = new ParcelMiniXMLHandler();
		MiniXMLParser parser = new MiniXMLParser(handler, automaton);
		parser.parse(is);
		return handler.getParcel();
	}

	public static void main(String[] args) {
		Example example = new Example();
		try {
			Parcel parcel = example.parse(EXAMPLE_FILE_NAME);
			System.out.println(parcel.getInfo());
		} catch (IOException | MiniXMLException e) {
			e.printStackTrace();
		}
	}
}
