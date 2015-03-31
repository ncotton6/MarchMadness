package model.data;

import datareader.CSVValue;

public class TourneySlot {
	private String season, slot, strongseed, weakseed;

	/**
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * @param season
	 *            the season to set
	 */
	@CSVValue(ColumnName = "SEASON", ColumnType = String.class)
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * @return the slot
	 */
	public String getSlot() {
		return slot;
	}

	/**
	 * @param slot
	 *            the slot to set
	 */
	@CSVValue(ColumnName = "SLOT", ColumnType = String.class)
	public void setSlot(String slot) {
		this.slot = slot;
	}

	/**
	 * @return the strongseed
	 */
	public String getStrongseed() {
		return strongseed;
	}

	/**
	 * @param strongseed
	 *            the strongseed to set
	 */
	@CSVValue(ColumnName = "STRONGSEED", ColumnType = String.class)
	public void setStrongseed(String strongseed) {
		this.strongseed = strongseed;
	}

	/**
	 * @return the weakseed
	 */
	public String getWeakseed() {
		return weakseed;
	}

	/**
	 * @param weakseed
	 *            the weakseed to set
	 */
	@CSVValue(ColumnName = "WEAKSEED", ColumnType = String.class)
	public void setWeakseed(String weakseed) {
		this.weakseed = weakseed;
	}
}
