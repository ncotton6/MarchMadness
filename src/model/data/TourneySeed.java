package model.data;

import datareader.CSVValue;

public class TourneySeed {

	private String season, seed;
	private int team;

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
	 * @return the seed
	 */
	public String getSeed() {
		return seed;
	}

	/**
	 * @param seed
	 *            the seed to set
	 */
	@CSVValue(ColumnName = "SEED", ColumnType = String.class)
	public void setSeed(String seed) {
		this.seed = seed;
	}

	/**
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * @param team
	 *            the team to set
	 */
	@CSVValue(ColumnName = "TEAM", ColumnType = Integer.class)
	public void setTeam(int team) {
		this.team = team;
	}
}
