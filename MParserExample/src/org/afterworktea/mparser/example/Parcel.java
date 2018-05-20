package org.afterworktea.mparser.example;

public class Parcel {
	private String parcelType = "";
	private String senderType = "";
	private String senderName = "";
	private String addresseeType = "";
	private String addresseeName = "";
	private String addresseeCity = "";

	private final static String INFO = "Parcel (%s) from %s (%s) to %s in %s (%s)";

	public Parcel() {

	}

	public String getInfo() {
		return String.format(INFO, parcelType, senderName, senderType, addresseeName, addresseeCity, addresseeType);
	}

	public String getParcelType() {
		return parcelType;
	}

	public void setParcelType(String parcelType) {
		this.parcelType = parcelType;
	}

	public String getSenderType() {
		return senderType;
	}

	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getAddresseeType() {
		return addresseeType;
	}

	public void setAddresseeType(String addresseeType) {
		this.addresseeType = addresseeType;
	}

	public String getAddresseeName() {
		return addresseeName;
	}

	public void setAddresseeName(String addresseeName) {
		this.addresseeName = addresseeName;
	}

	public String getAddresseeCity() {
		return addresseeCity;
	}

	public void setAddresseeCity(String addresseeCity) {
		this.addresseeCity = addresseeCity;
	}

}
