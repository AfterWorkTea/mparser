MParser
=======

## Welcome to the MParser!

The MParser is short for Mini (XML) Parser that combines important benefits:
1) Simplified but still very powerful grammar (reduced from 89 to 25 rules) that makes it much more intuitive and improves performance.
2) High performance and low memory usage of SAX (thanks to it's streaming nature)
3) Automatic nodes recognition allows (roughly speaking it's similar to a basic XPath, allows unique identification of elements in XML tree)
4) Efficient and small POJO implementations that lacks dependencies on third-part libraries.

MParser's grammar is based on one fundamental observation. Most IT people don't know nor take advantage of the full XML grammar. Therefore the simpler grammar is a balanced compromise that allows to parse most XMLs - for example the MParser works with Jenkins */api/xml files.

## MParser characteristic

The fundamental assumptions for MParser are:
* POJO - use plain Java objects for maximal compatibility.
* Implements own token and grammar engine from scratch, avoided dependencies on any fancy libraries.
* High performance and low memory consumption model with event messages similar to SAX.
* Powerful, built-in engine that allows to globally identify and distinguish XML elements while traversing an XML.
* Very simple language to explicitly define structure of XML (their elements like a very simple "XPath").
* Implements subset of XML grammar that guarantees parsing most of XML files.

All together it gives a parser that:
* Can parse most XML files (as long they uses only the selected rules).
* The jar takes only some KB and doesn't require any external classes.
* Combines advantages of both SAX and DOM: takes minimum resources and allows to unambiguously traverse XML elements.

Very loosely the MParser grammar can be expressed as:

* The system complies with XML elements: empty tag element or a sequence of start element, content and end element.
* A tag includes name. A start tag might also include attributes in addition to a name.
* Content can be empty or can include other elements, comment or a stream of char data.
* Escaping special characters is OK. See table 2 for details.
* The '<?xml' … '?>' sequence is accepted but there is no expectation that the system will act according to it.
* Works with ASCII and UTF-8 data.

Please note, MParser doesn't parse XML that includes CDATA.

More renounces:
* See [MParserExample - a working example with howto](https://github.com/AfterWorkTea/mparser/tree/master/MParserExample)
* See [Tokens and grammar](https://github.com/AfterWorkTea/mparser/wiki/Tokens-and-grammar)
* See [MParser Wiki](https://github.com/AfterWorkTea/mparser/wiki) for more information.


Thank you for visiting!

Robert

