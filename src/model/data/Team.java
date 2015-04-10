package model.data;

import datareader.CSVValue;

public class Team {
	private int id;
	private String name;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@CSVValue(ColumnName = "TEAM_ID", ColumnType = Integer.class)
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@CSVValue(ColumnName = "TEAM_NAME", ColumnType = String.class)
	public void setName(String name) {
		this.name = name;
	}
}
