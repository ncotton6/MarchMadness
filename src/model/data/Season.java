package model.data;

import datareader.CSVValue;

public class Season {

	private String season, wloc;
	private int daynum, wteam, wscore, lteam, lscore, numot;

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
	 * @return the wloc
	 */
	public String getWloc() {
		return wloc;
	}

	/**
	 * @param wloc
	 *            the wloc to set
	 */
	@CSVValue(ColumnName = "WLOC", ColumnType = String.class)
	public void setWloc(String wloc) {
		this.wloc = wloc;
	}

	/**
	 * @return the daynum
	 */
	public int getDaynum() {
		return daynum;
	}

	/**
	 * @param daynum
	 *            the daynum to set
	 */
	@CSVValue(ColumnName = "DAYNUM", ColumnType = Integer.class)
	public void setDaynum(int daynum) {
		this.daynum = daynum;
	}

	/**
	 * @return the wteam
	 */
	public int getWteam() {
		return wteam;
	}

	/**
	 * @param wteam
	 *            the wteam to set
	 */
	@CSVValue(ColumnName = "WTEAM", ColumnType = Integer.class)
	public void setWteam(int wteam) {
		this.wteam = wteam;
	}

	/**
	 * @return the wscore
	 */
	public int getWscore() {
		return wscore;
	}

	/**
	 * @param wscore
	 *            the wscore to set
	 */
	@CSVValue(ColumnName = "WSCORE", ColumnType = Integer.class)
	public void setWscore(int wscore) {
		this.wscore = wscore;
	}

	/**
	 * @return the lteam
	 */
	public int getLteam() {
		return lteam;
	}

	/**
	 * @param lteam
	 *            the lteam to set
	 */
	@CSVValue(ColumnName = "LTEAM", ColumnType = Integer.class)
	public void setLteam(int lteam) {
		this.lteam = lteam;
	}

	/**
	 * @return the lscore
	 */
	public int getLscore() {
		return lscore;
	}

	/**
	 * @param lscore
	 *            the lscore to set
	 */
	@CSVValue(ColumnName = "LSCORE", ColumnType = Integer.class)
	public void setLscore(int lscore) {
		this.lscore = lscore;
	}

	/**
	 * @return the numot
	 */
	public int getNumot() {
		return numot;
	}

	/**
	 * @param numot
	 *            the numot to set
	 */
	public void setNumot(int numot) {
		this.numot = numot;
	}

	@CSVValue(ColumnName = "NUMOT", ColumnType = String.class)
	public void setNumot(String numot) {
		this.numot = numot.equalsIgnoreCase("NA") ? 0 : new Integer(numot);
	}

}
