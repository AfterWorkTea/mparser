MParser
=======

# Welcome to the MParser!

The parser is based on one fundamental observation. Most IT people don't know nor take advantage of the full XML grammar.
In consequence a significant reduction of XML grammar (from 89 to 25 rules) still allows to parse most XML and it is much more intuitive.
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

## Tokens and Grammar

It is recommended to be familiar with XML http://www.w3.org/TR/REC-xml](http://www.w3.org/TR/REC-xml) document.

The rules for MParser are shown in table 1. Some reductions and simplifications as compared to XML are:
* Support only for characters #x9 | #xA | #xD | [#x20-#xFF]. That means support only for 8-bit, ASCII coding.
* No support for CDATA, type declaration, processing instructions, conditional sections etc.
* Limited support for prolog. The '<?xml' attributes '?>' sequence is parsed and returned to program but the parser is not acting upon it.

Table 1. Mini-XML grammar using Extended Backus-Naur Form (EBNF) notation.

`[#] Symbol ::= Expression`
* `[1] 	Document 	::= 	prolog element Misc*`
* `[2] 	Char 	::= 	#x9 | #xA | #xD | [#x20-#xFF]`
* `[3] 	S 	::= 	(#x20 | #x9 | #xD | #xA)+`
* `[4] 	NameChar 	::= 	Letter | Digit | '.' | '-' | '_' | ':'`
* `[5] 	Name 	::= 	(Letter | '_' | ':') (NameChar)*`
* `[6] 	CharData 	::= 	[^<&]*`
* `[7] 	Comment 	::= 	'<!--' ((Char - '-') | ('-' (Char - '-')))* '-->'`
* `[8] 	Prolog 	::= 	XMLDecl? Misc* (doctypedecl Misc*)?`
* `[9] 	XMLDecl 	::= 	'<?xml' VersionInfo? EncodingDecl? SDDecl? S? '?>'`
* `[10] 	VersionInfo 	::= 	S 'version' Eq ("'" VersionNum "'" | '"' VersionNum '"')`
* `[11] 	Eq 	::= 	S? '=' S?`
* `[12] 	VersionNum 	::= 	'1.0'`
* `[13] 	Misc 	::= 	Comment | S`
* `[14] 	EncodingDecl 	::= 	S 'encoding' Eq ('"' EncName '"' | "'" EncName "'" )`
* `[15] 	EncName 	::= 	[A-Za-z] ([A-Za-z0-9._] | '-')*`
* `[16] 	SDDecl 	::= 	S 'standalone' Eq (("'" ('yes' | 'no') "'") |('"' ('yes' | 'no') '"'))`
* `[17] 	Element 	::= 	EmptyElemTag | STag Content ETag`
* `[18] 	STag 	::= 	'<' Name (S Attribute)* S? '>'`
* `[19] 	Attribute 	::= 	Name Eq AttValue`
* `[20] 	AttValue 	::= 	'"' CharData '"' | "'" CharData "'"`
* `[21] 	ETag 	::= 	'</' Name S? '>'`
* `[22] 	Content 	::= 	CharData? ((Element | Comment) CharData?) *`
* `[23] 	EmptyElemTag 	::= 	'<' Name (S Attribute)* S? '/>'`
* `[24] 	Digit 	::= 	[#x30-#x39]`
* `[25] 	Letter 	::= 	[#x41-#x5A] | [#x61-#x7A] | [#xC0-#xD6] |[#xD8-#xF6] | [#xF8-#xFF]` 

Very loosely the rules can be expressed as:

* The system complies with XML elements: empty tag element or a sequence of start element, content and end element.
* A tag includes name. A start tag might also include attributes in addition to a name.
* Content can be empty or can include other elements, comment or a stream of char data.
* Escaping special characters is OK. See table 2 for details.
* The '<?xml' … '?>' sequence is accepted but there is no expectation that the system will act according to it.
* Works with ASCII and UTF-8 data.

Table 2. Escaping special characters.

`Character Escaping sequence`
* `">" 	"&gt;"`
* `"<" 	"&lt;"`
* `"&" 	"&amp;"`
* `apostrophe or single-quote character (') 	"&apos;"`
* `double-quote character (") 	"&quot;"`

Please note, that one of the most important idea about MParser is that every MParser data should be a valid XML.

You might check test examples of valid MParser. 

