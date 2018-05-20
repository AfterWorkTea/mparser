package org.afterworktea.mparser.example;

import org.afterworktea.mparser.MiniXMLAttrElement;
import org.afterworktea.mparser.MiniXMLEndElement;
import org.afterworktea.mparser.MiniXMLHandler;
import org.afterworktea.mparser.MiniXMLStartElement;

public class ParcelMiniXMLHandler implements MiniXMLHandler {

	private Parcel parcel;

	public ParcelMiniXMLHandler() {
	}

	public Parcel getParcel() {
		return parcel;
	}

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

	@Override
	public void endDocument(int arg0) {
		// do nothing
	}

	@Override
	public void endElement(MiniXMLEndElement element) {
		// do nothing
	}

	@Override
	public void startDocument() {
		parcel = new Parcel();
	}

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

	@Override
	public void xmlElement(MiniXMLAttrElement arg0) {
		// <xml?> do nothing
	}

}
