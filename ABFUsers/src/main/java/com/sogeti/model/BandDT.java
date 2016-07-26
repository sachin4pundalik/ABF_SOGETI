package com.sogeti.model;

public class BandDT {
	private int bandId;
	private String bandName;
	private int active;
	
	
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}

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
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}


}
