package model.data;

import datareader.CSVValue;

public class TourneySeed {

	private String season;
	private int team, seed, region;

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
	 * @param seed
	 *            the seed to set
	 */
	@CSVValue(ColumnName = "SEED", ColumnType = String.class)
	public void setSeed(String seed) {
		String r = seed.substring(0, 1);
		String s = seed.substring(1, 3);
		this.seed = Integer.valueOf(s);
		if ("W".equalsIgnoreCase(r)) {
			this.region = 0;
		} else if ("X".equalsIgnoreCase(r)) {
			this.region = 1;
		} else if ("Y".equalsIgnoreCase(r)) {
			this.region = 2;
		} else if ("Z".equalsIgnoreCase(r)) {
			this.region = 3;
		}
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

	/**
	 * @return the seed
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * @param seed the seed to set
	 */
	public void setSeed(int seed) {
		this.seed = seed;
	}

	/**
	 * @return the region
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(int region) {
		this.region = region;
	}
}
