package model;

import model.data.Loader;
import model.data.Team;

/**
 * {@link Link} is class the when provided primary key like attributes from a
 * CSV file it will create a link to the other data.
 * 
 * @author Nathaniel Cotton
 * 
 */
public class Link {

	/*
	 * Not all team data is in the teams.csv file, so there are a few teams in
	 * this years tournament that we won't have data for. This id will allow
	 * link to create temporary teams with an incrementing id, but it won't fill
	 * out data for them.
	 */
	private static int id = 100000;

	/**
	 * Given a team id the related team object will be returned. Null will be
	 * returned if the team doesn't exist.
	 * 
	 * @param i
	 * @return
	 */
	public static Team lookupTeam(int i) {
		for (Team t : Loader.teams) {
			if (t.getId() == i)
				return t;
		}
		return null;
	}

	/**
	 * Given an array of team names the respective ids for the teams will be
	 * looked up. They will then fill an array that will be returned.
	 * 
	 * @param teamNames
	 * @return
	 */
	public static int[] lookupTeams(String[] teamNames) {
		int[] teams = new int[teamNames.length];
		for (int i = 0; i < teamNames.length; ++i) {
			int team = lookupTeam(teamNames[i]);
			teams[i] = team;
		}
		return teams;
	}

	/**
	 * This method will look up a team based off its team name. If the team
	 * cannot be found than a new team with that name, and assigned id is
	 * created. Note no data will be associated with this team.
	 * 
	 * @param string
	 * @return
	 */
	private static int lookupTeam(String string) {
		for (Team t : Loader.teams) {
			if (t.getName().equalsIgnoreCase(string))
				return t.getId();
		}
		System.out.println(string + " wasn't found team, team being created");
		Team t = new Team();
		t.setId(id++);
		t.setName(string);
		Loader.teams.add(t);
		return t.getId();
	}

}
