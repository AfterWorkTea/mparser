MParser Example
===============

## Introduction

You will learn from the example how build your parser for an XML that reads a simple "parcel label" and returns a Parcel bean with it's data. In particular it will demonstrate
- the role and usage of MiniXMLAutomaton
- the role and usage of MiniXMLHandler
- reading an input XML file for parsing
- running MiniXMLParser and returning a Parcel bean

Included resources:
- parcel.xml - an example XML file to parse
- Parcel.java - Java bean class
- ParcelMiniXMLHandler.java - parcel handler for MParser
- Example.java - runnable class that demonstrate the parser

## Analise XML and declare XML structure

The MParser includes a key mechanism for node identification. It is responsibility of the MiniXMLAutomaton class. The class "learns" XML elements on the fly, but can be also pre-set to avoid any ambiguity. The constructor of MiniXMLAutomaton can take a String that declares structure of elements in XML. Here are the XML and the String for declaration:

``` XML
<?xml version="1.0" encoding="UTF-8" ?>
<parcel type="overnight">
	<sender type="company">
		<name>Shipping Company</name>
	</sender>
	<addressee type="home">
		<name>Adam</name>
		<city>Rome</city>
	</addressee>
</parcel>
```

``` Java
private final static String XML_AUTOMATON_STATES = "parcel(sender(name())addressee(name()city()))";
```

It declares, that the XML starts with "parcel" element, that includes "sender" or "addressee", the "sender" includes "name" and finally "addressee" includes "name" and "city". The notation is straightforward and uses parentheses for nesting elements according to XML structure. The "()" terminates a leaf element.

The order of elements is important as it defines their ID's, that are natural numbers starting with 0 for the root element "parcel", 3 for sender's name, 5 for addressee's name, etc.

## Build XML handler

Now we can prepare a dedicated MiniXMLHandler implementation that will receive stream of events. There are specific events for beginning/end of document, for beginning/end of element and for element's data. 

``` Java
public void startDocument()
public void endDocument(int arg0)
public void startElement(MiniXMLStartElement element)
public void endElement(MiniXMLEndElement element)
public void data(int state, String data)
```

Each element's event provides a state value that corespondents to element's ID as declared by an MiniXMLAutomaton. Therefore a dedicated handler object "knows" the localization and can take adequate actions to handle the data, e.g. pass it to proper Parcel's setters methods.

For instance, the following will match Parcel setters (please note the ID's in switch as declared above):

``` Java
@Override
public void data(int state, String data) {
	switch (state) {
	case 2:
		parcel.setSenderName(data);
		break;
	case 4:
		parcel.setAddresseeName(data);
		break;
	case 5:
		parcel.setAddresseeCity(data);
		break;
	default:
		break;
	}
}
```

And reads attributes from XML:

``` Java
@Override
public void startElement(MiniXMLStartElement element) {
	switch (element.getStateID()) {
	case 0:
		parcel.setParcelType(element.get("type"));
		break;
	case 1:
		parcel.setSenderType(element.get("type"));
		break;
	case 4:
		parcel.setAddresseeType(element.get("type"));
		break;
	default:
		break;
	}
}
```

The startDocument can be used to instantiate a Parcel bean (a member of the handler class)

``` Java
private Parcel parcel;

public Parcel getParcel() {
	return parcel;
}

@Override
public void startDocument() {
	parcel = new Parcel();
}
```

That's it. The ParcelMiniXMLHandler is ready.

## Run the parser

The components are ready to run the parser, we just need to read the XML file:

``` Java
File file = new File(fileName);
InputStream is = new FileInputStream(file);
```

And prepare the objects:

``` Java
private final static String XML_AUTOMATON_STATES = "parcel(sender(name())addressee(name()city()))";

MiniXMLAutomaton automaton = new MiniXMLAutomatonImpl(XML_AUTOMATON_STATES);
ParcelMiniXMLHandler handler = new ParcelMiniXMLHandler();
MiniXMLParser parser = new MiniXMLParser(handler, automaton);
```

Now we can run the parser and ask handler to return the parcel:

``` Java
parser.parse(is);
return handler.getParcel();
```

Please remember to include the MParser to the example.
