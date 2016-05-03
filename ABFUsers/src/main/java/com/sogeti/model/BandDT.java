package com.sogeti.model;

public class BandDT {
	private int bandId;
	private String bandName;
	
	public int getBandId() {
		return bandId;
	}

	public void setBandId(int bandId) {
		this.bandId = bandId;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BandDT [bandId=");
		builder.append(bandId);
		builder.append(", bandName=");
		builder.append(bandName);
		builder.append("]");
		return builder.toString();
	}


}
