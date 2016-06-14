MParser
=======

## Welcome to the MParser!

The MParser is short for Mini (XML) Parser. It is based on one fundamental observation. 
Most IT people don't know nor take advantage of the full XML grammar.
In consequence a significant reduction of XML grammar (from 89 to 25 rules) allows to parse most XMLs and makes it much more intuitive.
For example the MParser works with the Jenkins */api/xml files.

## MParser characteristic

The fundamental assumptions for MParser are:
* POJO - use plain Java objects for maximal compatibility.
* Implements own token and grammar engine from scratch, avoided dependencies on any fancy classes.
* High performance and low memory consumption model with event messages similar to SAX.
* Powerful, built-in engine that allows to globally identify and distinguish XML elements.
* Very simple language to explicitly define structure of XML (their elements like very simple XPath).
* Implements subset of XML grammar that grantees parsing most of XML files.

All together it gives a parser that:
* Can parse most XML files (as long they uses only the selected rules).
* The jar takes only some KB and doesn't require any external classes.
* Combines advantages of both SAX and DOM: takes minimum resources and allows to unambiguously address XML elements.

Very loosely the that can be expressed as:

* The system complies with XML elements: empty tag element or a sequence of start element, content and end element.
* A tag includes name. A start tag might also include attributes in addition to a name.
* Content can be empty or can include other elements, comment or a stream of char data.
* Escaping special characters is OK. See table 2 for details.
* The '<?xml' … '?>' sequence is accepted but there is no expectation that the system will act according to it.
* Works with ASCII and UTF-8 data.

See [https://github.com/AfterWorkTea/mparser/wiki](https://github.com/AfterWorkTea/mparser/wiki) for more information.

Thank you for visiting!
Robert

