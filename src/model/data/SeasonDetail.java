package model.data;

import datareader.CSVValue;

public class SeasonDetail {
	private String season, year, dayzero, regionW, regionX, regionY, regionZ;

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
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	@CSVValue(ColumnName = "YEARS", ColumnType = String.class)
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the dayzero
	 */
	public String getDayzero() {
		return dayzero;
	}

	/**
	 * @param dayzero
	 *            the dayzero to set
	 */
	@CSVValue(ColumnName = "DAYZERO", ColumnType = String.class)
	public void setDayzero(String dayzero) {
		this.dayzero = dayzero;
	}

	/**
	 * @return the regionW
	 */
	public String getRegionW() {
		return regionW;
	}

	/**
	 * @param regionW
	 *            the regionW to set
	 */
	@CSVValue(ColumnName = "REGIONW", ColumnType = String.class)
	public void setRegionW(String regionW) {
		this.regionW = regionW;
	}

	/**
	 * @return the regionX
	 */
	public String getRegionX() {
		return regionX;
	}

	/**
	 * @param regionX
	 *            the regionX to set
	 */
	@CSVValue(ColumnName = "REGIONX", ColumnType = String.class)
	public void setRegionX(String regionX) {
		this.regionX = regionX;
	}

	/**
	 * @return the regionY
	 */
	public String getRegionY() {
		return regionY;
	}

	/**
	 * @param regionY
	 *            the regionY to set
	 */
	@CSVValue(ColumnName = "REGIONY", ColumnType = String.class)
	public void setRegionY(String regionY) {
		this.regionY = regionY;
	}

	/**
	 * @return the regionZ
	 */
	public String getRegionZ() {
		return regionZ;
	}

	/**
	 * @param regionZ
	 *            the regionZ to set
	 */
	@CSVValue(ColumnName = "REGIONZ", ColumnType = String.class)
	public void setRegionZ(String regionZ) {
		this.regionZ = regionZ;
	}
}
