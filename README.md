mparser
=======

# Welcome to the MParser wiki!

POJO parser for a grammar that derives from adopting a subset of the XML rules.

## Tokens and Grammar

It is recommended to be familiar with XML documents available at [http://www.w3.org/TR/REC-xml](http://www.w3.org/TR/REC-xml).

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
* A tag includes name. A start tag might include attributes in addition to a name.
* Content can be empty or can include other elements, comment or a stream of char data.
* Escaping special characters is OK. See table 2 for details.
* The '<?xml' … '?>' sequence is accepted but there is no expectation that the system will act according to it.
* Only 8-bits ASCII characters coding is accepted.

Table 2. Escaping special characters.

`Character Escaping sequence`
* `">" 	"&gt;"`
* `"<" 	"&lt;"`
* `"&" 	"&amp;"`
* `apostrophe or single-quote character (') 	"&apos;"`
* `double-quote character (") 	"&quot;"`

Please note, that one of the most important idea about MParser is that every MParser data should be a valid XML.

You might check test examples of valid MParser. 

